package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 18 Jun 2018
 */

public class OWLClassDeserializer extends StdDeserializer<OWLClass> {

    private final OWLEntityDeserializer<OWLClass> deserializer;

    public OWLClassDeserializer(OWLDataFactory dataFactory) {
        super(OWLClass.class);
        deserializer = new OWLEntityDeserializer<>(dataFactory);
    }

    @Override
    public OWLClass deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return deserializer.deserialize(jsonParser, EntityType.CLASS);
    }

    @Override
    public Object deserializeWithType(JsonParser jsonParser,
                                      DeserializationContext ctxt,
                                      TypeDeserializer typeDeserializer) throws IOException {
        return deserializer.deserialize(jsonParser, EntityType.CLASS);
    }
}
