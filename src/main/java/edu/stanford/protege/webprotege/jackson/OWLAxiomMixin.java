package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.semanticweb.owlapi.model.*;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonSubTypes({
        @Type(OWLSubClassOfAxiom.class),
        @Type(OWLEquivalentClassesAxiom.class),
        @Type(OWLDisjointClassesAxiom.class),
        @Type(OWLHasKeyAxiom.class),
        @Type(OWLDeclarationAxiom.class),
        @Type(OWLClassAssertionAxiom.class),
        @Type(OWLAnnotationAssertionAxiom.class),
        @Type(OWLObjectPropertyAssertionAxiom.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public interface OWLAxiomMixin {

}
