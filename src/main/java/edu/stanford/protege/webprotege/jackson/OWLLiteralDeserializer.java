package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
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

        var tree = (ObjectNode) jsonParser.readValueAsTree();
        var value = tree.get("value");
        var lang = tree.get("lang");
        var iri = tree.get("datatype");
        if(iri == null) {
            // Fallback to legacy property name
            iri = tree.get("type");
        }

        if(value == null) {
            throw new JsonParseException(jsonParser, "value field is missing");
        }
        if(lang != null) {
            return dataFactory.getOWLLiteral(value.asText(), lang.textValue());
        }
        if(iri != null) {
            return dataFactory.getOWLLiteral(value.asText(), dataFactory.getOWLDatatype(IRI.create(iri.textValue())));
        }
        return dataFactory.getOWLLiteral(value.asText(), "");
    }
}
