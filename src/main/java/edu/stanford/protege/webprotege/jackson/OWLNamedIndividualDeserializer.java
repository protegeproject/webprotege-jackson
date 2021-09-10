package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.semanticweb.owlapi.model.EntityType;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-05
 */
public class OWLNamedIndividualDeserializer extends StdDeserializer<OWLNamedIndividual> {

    private final OWLEntityDeserializer<OWLNamedIndividual> entityDeserializer;

    public OWLNamedIndividualDeserializer(OWLEntityDeserializer<OWLNamedIndividual> entityDeserializer) {
        super(OWLNamedIndividual.class);
        this.entityDeserializer = entityDeserializer;
    }

    @Override
    public OWLNamedIndividual deserialize(JsonParser p,
                                          DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return entityDeserializer.deserialize(p, EntityType.NAMED_INDIVIDUAL);
    }

    @Override
    public Object deserializeWithType(JsonParser p,
                                      DeserializationContext ctxt,
                                      TypeDeserializer typeDeserializer) throws IOException {
        return entityDeserializer.deserialize(p, EntityType.NAMED_INDIVIDUAL);
    }
}
