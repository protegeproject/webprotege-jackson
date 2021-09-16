package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonTypeName("ObjectPropertyRange")
public class OWLObjectPropertyRangeAxiomImplMixin {

    @JsonCreator
    public OWLObjectPropertyRangeAxiomImplMixin(@JsonProperty("property") @Nonnull OWLObjectPropertyExpression property,
                                                @JsonProperty("range") @Nonnull OWLClassExpression range,
                                                @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Set<? extends OWLAnnotation> annotations) {
    }
}
