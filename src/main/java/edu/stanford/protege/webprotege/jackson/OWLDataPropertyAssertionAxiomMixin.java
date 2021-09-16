package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyAssertionAxiomImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonDeserialize(as = OWLDataPropertyAssertionAxiomImpl.class)
@JsonIncludeProperties({"subject", "property", "object", "annotations"})
@JsonTypeName("DataPropertyAssertion")
public interface OWLDataPropertyAssertionAxiomMixin {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("annotations")
    Set<OWLAnnotation> getAnnotations();
}
