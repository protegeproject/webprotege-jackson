package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import org.semanticweb.owlapi.model.IRI;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 18 Jun 2018
 */

public class IriDeserializer extends StdDeserializer<IRI> {

    public IriDeserializer() {
        super(IRI.class);
    }

    @Override
    public IRI deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        // IRIs can be serialized as plain strings or as typed objects, depending upon the context
        // Check for an object and if it's not an object assume a plain string
        if(jsonParser.currentToken() == JsonToken.START_OBJECT) {
            String value = null;
            String type = null;
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                var fieldName = jsonParser.getCurrentName();
                if("value".equals(fieldName)) {
                    jsonParser.nextToken();
                    value = jsonParser.readValueAs(String.class);
                }
                else if("@type".equals(fieldName) || "type".equals(fieldName)) {
                    jsonParser.nextToken();
                    jsonParser.readValueAs(String.class);
                }
                if(fieldName == null) {
                    break;
                }
            }
            if(value == null) {
                throw new JsonParseException(jsonParser, "Missing value field that contains the lexical value for the IRI");
            }
            return IRI.create(value);
        }
        else {
            // Plain String IRI
            return IRI.create(jsonParser.getValueAsString());
        }

    }
}

