package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-19
 */
@JsonComponent
public class OWLAnnotationPropertyDeserializer extends StdDeserializer<OWLAnnotationProperty> {

    private final OWLEntityDeserializer<OWLAnnotationProperty> deserializer;

    public OWLAnnotationPropertyDeserializer(OWLDataFactory dataFactory) {
        super(OWLClass.class);
        deserializer = new OWLEntityDeserializer<>(dataFactory);
    }

    @Override
    public OWLAnnotationProperty deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return deserializer.deserialize(jsonParser, deserializationContext);
    }
}
