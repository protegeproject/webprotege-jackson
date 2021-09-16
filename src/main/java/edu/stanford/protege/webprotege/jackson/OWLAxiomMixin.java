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
        @Type(OWLObjectPropertyAssertionAxiom.class),
        @Type(OWLDataPropertyAssertionAxiom.class),
        @Type(OWLSubObjectPropertyOfAxiom.class),
        @Type(OWLSubDataPropertyOfAxiom.class),
        @Type(OWLSubAnnotationPropertyOfAxiom.class),
        @Type(OWLFunctionalObjectPropertyAxiom.class),
        @Type(OWLFunctionalDataPropertyAxiom.class),
        @Type(OWLInverseFunctionalObjectPropertyAxiom.class),
        @Type(OWLAsymmetricObjectPropertyAxiom.class),
        @Type(OWLSymmetricObjectPropertyAxiom.class),
        @Type(OWLTransitiveObjectPropertyAxiom.class),
        @Type(OWLIrreflexiveObjectPropertyAxiom.class),
        @Type(OWLSameIndividualAxiom.class),
        @Type(OWLDifferentIndividualsAxiom.class),
        @Type(OWLDisjointObjectPropertiesAxiom.class),
        @Type(OWLDisjointDataPropertiesAxiom.class),
        @Type(OWLObjectPropertyRangeAxiom.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public interface OWLAxiomMixin {

}
