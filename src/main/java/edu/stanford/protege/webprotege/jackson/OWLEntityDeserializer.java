package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.springframework.boot.jackson.JsonComponent;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 18 Jun 2018
 */
@JsonComponent
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
        return deserialize(jsonParser);
    }

    protected E deserialize(JsonParser jsonParser) throws IOException {
        EntityType<?> type = null;
        IRI iri = null;
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.getCurrentName();
            if (OWLEntitySerializer.TYPE_FIELD_NAME.equals(fieldname) || TYPE_FIELD_LEGACY_NAME.equals(fieldname)) {
                jsonParser.nextToken();
                type = jsonParser.readValueAs(EntityType.class);
            }
            else if(OWLEntitySerializer.IRI_FIELD_NAME.equals(fieldname)) {
                jsonParser.nextToken();
                iri = jsonParser.readValueAs(IRI.class);
            }
        }
        if (type != null && iri != null) {
            return (E) dataFactory.getOWLEntity(type, iri);
        }
        else {
            if(type == null) {
                throw new JsonParseException(jsonParser, OWLEntitySerializer.TYPE_FIELD_NAME + " field is missing");
            }
            else {
                throw new JsonParseException(jsonParser, OWLEntitySerializer.IRI_FIELD_NAME + " field is missing");
            }
        }
    }
}
