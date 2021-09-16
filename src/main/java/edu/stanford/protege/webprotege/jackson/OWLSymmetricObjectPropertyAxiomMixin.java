package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import uk.ac.manchester.cs.owl.owlapi.OWLAsymmetricObjectPropertyAxiomImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLSymmetricObjectPropertyAxiomImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonTypeName("SymmetricObjectProperty")
@JsonDeserialize(as = OWLSymmetricObjectPropertyAxiomImpl.class)
@JsonIncludeProperties({"property", "annotations"})
public interface OWLSymmetricObjectPropertyAxiomMixin {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Set<OWLAnnotation> getAnnotations();
}
