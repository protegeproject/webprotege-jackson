package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectComplementOfImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("ObjectComplementOf")
@JsonDeserialize(as = OWLObjectComplementOfImpl.class)
@JsonIncludeProperties({"classExpression"})
public interface OWLObjectComplementOfMixin {

    @JsonProperty("classExpression")
    OWLClassExpression getOperand();
}
