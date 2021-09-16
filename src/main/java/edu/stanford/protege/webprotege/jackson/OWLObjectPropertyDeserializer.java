package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-13
 */
public class OWLObjectPropertyDeserializer extends StdDeserializer<OWLObjectPropertyImpl> {


    private final OWLEntityDeserializer<OWLObjectProperty> deserializer;

    public OWLObjectPropertyDeserializer(OWLDataFactory dataFactory) {
        super(OWLObjectPropertyImpl.class);
        deserializer = new OWLEntityDeserializer<>(dataFactory);
    }

    @Override
    public OWLObjectPropertyImpl deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return (OWLObjectPropertyImpl) deserializer.deserialize(jsonParser, EntityType.OBJECT_PROPERTY);
    }

    @Override
    public Object deserializeWithType(JsonParser jsonParser,
                                      DeserializationContext ctxt,
                                      TypeDeserializer typeDeserializer) throws IOException {
        return deserializer.deserialize(jsonParser, EntityType.OBJECT_PROPERTY);
    }
}
