package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLAnnotationValue;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonTypeName("AnnotationAssertion")
public class OWLAnnotationAssertionAxiomImplMixin {

    @JsonCreator
    public OWLAnnotationAssertionAxiomImplMixin(
            @JsonProperty("subject") OWLAnnotationSubject subject,
            @JsonProperty("property") OWLAnnotationProperty property,
            @JsonProperty("value") OWLAnnotationValue value,
            @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Collection<? extends OWLAnnotation> annotations) {
    }
}
