package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
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
                                   @JsonProperty("annotations") @JsonSetter(nulls = Nulls.AS_EMPTY) Collection<? extends OWLAnnotation> annotations) {
    }
}
