package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClassExpression;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonTypeName("DisjointClasses")
public class OWLDisjointClassesAxiomImplMixin {

    @JsonCreator
    public OWLDisjointClassesAxiomImplMixin(@JsonProperty("classExpressions") Set<? extends OWLClassExpression> classExpressions,
                                            @JsonProperty("annotations")Set<? extends OWLAnnotation> annotations) {
    }
}
