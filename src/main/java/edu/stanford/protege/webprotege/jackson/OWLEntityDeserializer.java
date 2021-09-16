package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.google.common.collect.ImmutableMap;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 18 Jun 2018
 */
public class OWLEntityDeserializer<E extends OWLEntity> extends StdDeserializer<E> {


    private static ImmutableMap<String, EntityType<?>> buildTypeMap() {
        var mapBuilder = ImmutableMap.<String, EntityType<?>>builder();
        for(var entityType : EntityType.values()) {
            mapBuilder.put(entityType.getName(), entityType);
            mapBuilder.put(entityType.getPrefixedName(), entityType);
        }
        return mapBuilder.build();
    }

    private static final String TYPE_FIELD_LEGACY_NAME = "type";

    private static ImmutableMap<String, EntityType<?>> entityTypeMap = buildTypeMap();


    @Nonnull
    private final OWLDataFactory dataFactory;

    private final EntityType<?> defaultEntityType;


    public OWLEntityDeserializer(@Nonnull OWLDataFactory dataFactory) {
        this(dataFactory, null);
    }

    public OWLEntityDeserializer(@Nonnull OWLDataFactory dataFactory, EntityType<?> defaultEntityType) {
        super(OWLEntity.class);
        this.dataFactory = dataFactory;
        this.defaultEntityType = defaultEntityType;
    }

    @Override
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return deserialize(jsonParser, (EntityType<?>) null);
    }

    public E deserialize(JsonParser jsonParser, EntityType<?> entityType) throws IOException {
        var tree = jsonParser.readValueAsTree();



        EntityType<?> type = entityType;
        String iri = null;

        if(tree.isObject()) {
            var objectNode = (ObjectNode) tree;
            iri = objectNode.get("iri").asText();
            var typeValue = objectNode.get("@type");
            if(typeValue == null) {
                typeValue = objectNode.get("type");
            }
            if(typeValue != null) {
                type = entityTypeMap.get(typeValue.textValue());
            }
            else {
                type = defaultEntityType;
            }
        }
        else if(tree.isValueNode()) {
            var valueNode = (ValueNode) tree;
            iri = valueNode.textValue();
            type = defaultEntityType;
        }

        if (iri != null && type != null) {
            return (E) dataFactory.getOWLEntity(type, IRI.create(iri));
        }
        else {
            if (type == null) {
                throw new JsonParseException(jsonParser, "@type field is missing");
            }
            else {
                throw new JsonParseException(jsonParser, "iri field is missing");
            }
        }
    }


    @Override
    public Object deserializeWithType(JsonParser p,
                                      DeserializationContext ctxt,
                                      TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, (EntityType<?>) null);
    }
}
