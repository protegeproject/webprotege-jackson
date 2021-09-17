package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import uk.ac.manchester.cs.owl.owlapi.OWLNegativeObjectPropertyAssertionAxiomImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("NegativeObjectPropertyAssertion")
@JsonDeserialize(as = OWLNegativeObjectPropertyAssertionAxiomImpl.class)
@JsonIncludeProperties({"subject", "property", "object", "annotations"})
public interface OWLNegativeObjectPropertyAssertionAxiomMixin {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("annotations")
    public abstract Set<OWLAnnotation> getAnnotations();
}
