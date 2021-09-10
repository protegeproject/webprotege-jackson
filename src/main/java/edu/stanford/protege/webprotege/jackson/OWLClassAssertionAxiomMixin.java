package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import uk.ac.manchester.cs.owl.owlapi.OWLClassAssertionAxiomImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-04
 */
@JsonSubTypes({@Type(value = OWLClassAssertionAxiomImpl.class)})
@JsonIncludeProperties({"classExpression", "individual", "annotations"})
public abstract class OWLClassAssertionAxiomMixin {

}