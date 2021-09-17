package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import uk.ac.manchester.cs.owl.owlapi.OWLNegativeDataPropertyAssertionAxiomImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("NegativeDataPropertyAssertion")
@JsonDeserialize(as = OWLNegativeDataPropertyAssertionAxiomImpl.class)
@JsonIncludeProperties({"subject", "property", "object", "annotations"})
public interface OWLNegativeDataPropertyAssertionAxiomMixin {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("annotations")
    Set<OWLAnnotation> getAnnotations();
}
