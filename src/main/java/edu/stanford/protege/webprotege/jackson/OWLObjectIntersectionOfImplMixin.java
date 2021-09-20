package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLClassExpression;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("ObjectIntersectionOf")
public class OWLObjectIntersectionOfImplMixin {

    @JsonCreator
    public OWLObjectIntersectionOfImplMixin(@JsonProperty("classExpressions") @Nonnull Set<? extends OWLClassExpression> operands) {
    }
}
