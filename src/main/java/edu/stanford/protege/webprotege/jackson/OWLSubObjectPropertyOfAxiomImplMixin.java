package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
public class OWLSubObjectPropertyOfAxiomImplMixin {

    @JsonCreator
    public OWLSubObjectPropertyOfAxiomImplMixin(
            @JsonProperty("subProperty") @Nonnull OWLObjectPropertyExpression subProperty,
            @JsonProperty("superProperty") @Nonnull OWLObjectPropertyExpression superProperty,
            @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Collection<? extends OWLAnnotation> annotations) {
    }
}
