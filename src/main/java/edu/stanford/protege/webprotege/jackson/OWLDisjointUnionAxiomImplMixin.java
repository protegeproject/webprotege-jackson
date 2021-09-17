package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("DisjointUnion")
public class OWLDisjointUnionAxiomImplMixin {

    @JsonCreator
    public OWLDisjointUnionAxiomImplMixin(@JsonProperty("class") @Nonnull OWLClass owlClass,
                                          @JsonProperty("classExpressions") @Nonnull Set<? extends OWLClassExpression> classExpressions,
                                          @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Set<? extends OWLAnnotation> annotations) {
    }
}
