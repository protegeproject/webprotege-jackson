package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.springframework.boot.jackson.JsonComponent;

import javax.annotation.Nonnull;
import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-12-04
 */

public class OWLLiteralDeserializer extends StdDeserializer<OWLLiteral> {

    @Nonnull
    private final OWLDataFactory dataFactory;

    public OWLLiteralDeserializer(@Nonnull OWLDataFactory dataFactory) {
        super(OWLLiteral.class);
        this.dataFactory = dataFactory;
    }

    @Override
    public Object deserializeWithType(JsonParser p,
                                      DeserializationContext ctxt,
                                      TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }

    @Override
    public OWLLiteral deserialize(JsonParser jsonParser,
                                  DeserializationContext ctxt) throws IOException {

        String value = null;
        String lang = null;
        String iri = null;
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String fieldname = jsonParser.getCurrentName();
            if ("lang".equals(fieldname)) {
                jsonParser.nextToken();
                lang = jsonParser.readValueAs(String.class);
            }
            else if("value".equals(fieldname)) {
                jsonParser.nextToken();
                value = jsonParser.readValueAs(String.class);
            }
            else if("@type".equals(fieldname) || "type".equals(fieldname)) {
                jsonParser.nextToken();
                iri = jsonParser.readValueAs(String.class);
            }
            if(fieldname == null) {
                break;
            }
        }
        if(value == null) {
            throw new JsonParseException(jsonParser, "value field is missing");
        }
        if(lang != null) {
            return dataFactory.getOWLLiteral(value, lang);
        }
        if(iri != null) {
            return dataFactory.getOWLLiteral(value, dataFactory.getOWLDatatype(IRI.create(iri)));
        }
        return dataFactory.getOWLLiteral(value, "");
    }
}
