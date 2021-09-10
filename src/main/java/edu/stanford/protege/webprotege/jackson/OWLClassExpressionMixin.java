package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.semanticweb.owlapi.model.*;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonSubTypes({
        @JsonSubTypes.Type(value = OWLClass.class),
        @JsonSubTypes.Type(value = OWLObjectSomeValuesFrom.class),
        @JsonSubTypes.Type(value = OWLObjectAllValuesFrom.class),
        @JsonSubTypes.Type(value = OWLObjectMinCardinality.class),
        @JsonSubTypes.Type(value = OWLObjectMaxCardinality.class),
        @JsonSubTypes.Type(value = OWLObjectExactCardinality.class),
        @JsonSubTypes.Type(value = OWLObjectHasValue.class),
        @JsonSubTypes.Type(value = OWLObjectHasSelf.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true)
public interface OWLClassExpressionMixin {

}
