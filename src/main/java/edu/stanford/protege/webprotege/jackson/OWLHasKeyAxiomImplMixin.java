package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.Nulls;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLPropertyExpression;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("HasKey")
public class OWLHasKeyAxiomImplMixin {

    public OWLHasKeyAxiomImplMixin(@JsonProperty("classExpression") @Nonnull OWLClassExpression expression,
                                   @JsonProperty("propertyExpressions") @Nonnull Set<? extends OWLPropertyExpression> propertyExpressions,
                                   @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Collection<? extends OWLAnnotation> annotations) {
    }
}
