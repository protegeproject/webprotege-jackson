package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLClassExpression;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("ObjectComplementOf")
public class OWLObjectComplementOfImplMixin {

    @JsonCreator
    public OWLObjectComplementOfImplMixin(@JsonProperty("classExpression") OWLClassExpression classExpression) {
    }
}
