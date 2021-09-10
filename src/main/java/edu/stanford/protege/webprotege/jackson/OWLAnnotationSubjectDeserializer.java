package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
public class OWLAnnotationSubjectDeserializer extends StdDeserializer<OWLAnnotationSubject> {

    private IriDeserializer iriDeserializer = new IriDeserializer();

    public OWLAnnotationSubjectDeserializer() {
        super(OWLAnnotationSubject.class);
    }

    @Override
    public OWLAnnotationSubject deserialize(JsonParser p,
                                            DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if(p.hasToken(JsonToken.START_OBJECT)) {
            throw new RuntimeException();
        }
        else {
            return iriDeserializer.deserialize(p, ctxt);
        }
    }
}
