package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationAssertionAxiomImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonSubTypes({@Type(value = OWLAnnotationAssertionAxiomImpl.class)})
@JsonIncludeProperties({"subject", "property", "value", "annotations"})
public abstract class OWLAnnotationAssertionAxiomMixin {
}