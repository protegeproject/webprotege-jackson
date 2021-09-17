package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("AnnotationPropertyRange")
public class OWLAnnotationPropertyRangeAxiomImplMixin {

    @JsonCreator
    public OWLAnnotationPropertyRangeAxiomImplMixin(@JsonProperty("property") @Nonnull OWLAnnotationProperty property,
                                                    @JsonProperty("range") @Nonnull IRI range,
                                                    @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Collection<? extends OWLAnnotation> annotations) {
    }
}
