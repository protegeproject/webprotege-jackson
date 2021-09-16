package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonTypeName("SubAnnotationPropertyOf")
public abstract class OWLSubAnnotationPropertyOfAxiomImplMixin {

    @JsonCreator
    public OWLSubAnnotationPropertyOfAxiomImplMixin(@JsonProperty("subProperty") @Nonnull OWLAnnotationProperty subProperty,
                                                    @JsonProperty("superProperty") @Nonnull OWLAnnotationProperty superProperty,
                                                    @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Collection<? extends OWLAnnotation> annotations) {
    }
}
