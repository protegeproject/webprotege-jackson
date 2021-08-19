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
            if ("type".equals(fieldname)) {
                jsonParser.nextToken();
                type = jsonParser.readValueAs(EntityType.class);
            }
            else if("iri".equals(fieldname)) {
                jsonParser.nextToken();
                iri = jsonParser.readValueAs(IRI.class);
            }
        }
        if (type != null && iri != null) {
            return (E) dataFactory.getOWLEntity(type, iri);
        }
        else {
            if(type == null) {
                throw new JsonParseException(jsonParser, "type field is missing");
            }
            else {
                throw new JsonParseException(jsonParser, "iri field is missing");
            }
        }
    }
}
