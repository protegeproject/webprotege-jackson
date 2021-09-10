package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClassExpression;

import java.util.Collection;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonTypeName("SubClassOf")
public class OWLSubClassOfAxiomImplMixin {


    @JsonCreator
    public OWLSubClassOfAxiomImplMixin(@JsonProperty("subClass") OWLClassExpression subClass,
                                   @JsonProperty("superClass") OWLClassExpression superClass,
                                   @JsonProperty("annotations") Collection<? extends OWLAnnotation> annotations) {
    }
}
