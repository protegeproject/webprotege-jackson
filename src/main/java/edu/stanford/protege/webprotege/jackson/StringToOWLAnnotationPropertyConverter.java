package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationPropertyImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
public class StringToOWLAnnotationPropertyConverter extends StdConverter<String, OWLAnnotationProperty> {

    @Override
    public OWLAnnotationProperty convert(String value) {
        return new OWLAnnotationPropertyImpl(IRI.create(value));
    }
}
