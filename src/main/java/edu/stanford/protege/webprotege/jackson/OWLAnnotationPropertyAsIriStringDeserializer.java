package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationPropertyImpl;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-12
 */
public class OWLAnnotationPropertyAsIriStringDeserializer extends StdDeserializer<Object> {

    public OWLAnnotationPropertyAsIriStringDeserializer() {
        super(Object.class);
    }

    @Override
    public OWLAnnotationProperty deserialize(JsonParser p,
                                             DeserializationContext ctxt) throws IOException, JsonProcessingException {
        var iriString = p.getValueAsString();
        var iri = IRI.create(iriString);
        return new OWLAnnotationPropertyImpl(iri);
    }
}
