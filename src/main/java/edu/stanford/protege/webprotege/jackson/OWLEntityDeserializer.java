package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
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

    private static final String TYPE_FIELD_LEGACY_NAME = "type";

    @Nonnull
    private final OWLDataFactory dataFactory;

    public OWLEntityDeserializer(@Nonnull OWLDataFactory dataFactory) {
        super(OWLEntity.class);
        this.dataFactory = dataFactory;
    }

    @Override
    public E deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return deserialize(jsonParser, (EntityType<?>) null);
    }

    public E deserialize(JsonParser jsonParser, EntityType<?> entityType) throws IOException {
        EntityType<?> type = entityType;
        String iri = null;
        while (true) {
            var token = jsonParser.nextToken();
            if(JsonToken.END_OBJECT.equals(token)) {
                break;
            }
            if(JsonToken.VALUE_STRING.equals(token)) {
                if(isTypeField(jsonParser.currentName())) {
                    type = jsonParser.readValueAs(EntityType.class);

                }
                else if(isIriField(jsonParser.currentName())) {
                    iri = jsonParser.getText();
                }
            }

        }
        if (type != null && iri != null) {
            return (E) dataFactory.getOWLEntity(type, IRI.create(iri));
        }
        else {
            if (type == null) {
                throw new JsonParseException(jsonParser, OWLEntitySerializer.TYPE_FIELD_NAME + " field is missing");
            }
            else {
                throw new JsonParseException(jsonParser, OWLEntitySerializer.IRI_FIELD_NAME + " field is missing");
            }
        }
    }

    private static boolean isIriField(String fieldname) {
        return OWLEntitySerializer.IRI_FIELD_NAME.equals(fieldname);
    }

    private static boolean isTypeField(String fieldname) {
        return OWLEntitySerializer.TYPE_FIELD_NAME.equals(fieldname) || TYPE_FIELD_LEGACY_NAME.equals(fieldname);
    }

    @Override
    public Object deserializeWithType(JsonParser p,
                                      DeserializationContext ctxt,
                                      TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, (EntityType<?>) null);
    }
}
