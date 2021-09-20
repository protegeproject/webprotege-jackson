package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLClassExpression;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectIntersectionOfImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("ObjectIntersectionOf")
@JsonDeserialize(as = OWLObjectIntersectionOfImpl.class)
@JsonIncludeProperties({"classExpressions"})
public interface OWLObjectIntersectionOfMixin {

    @JsonProperty("classExpressions")
    Set<OWLClassExpression> getOperands();
}
