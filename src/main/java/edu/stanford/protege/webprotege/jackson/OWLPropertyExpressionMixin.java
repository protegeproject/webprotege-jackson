package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @Type(OWLDataPropertyExpression.class),
        @Type(OWLObjectPropertyExpression.class)
})
public interface OWLPropertyExpressionMixin {

}
