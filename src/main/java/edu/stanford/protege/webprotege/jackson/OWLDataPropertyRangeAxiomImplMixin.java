package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.*;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonTypeName("DataPropertyRange")
public class OWLDataPropertyRangeAxiomImplMixin {

    @JsonCreator
    public OWLDataPropertyRangeAxiomImplMixin(
            @JsonProperty("property") @Nonnull OWLDataPropertyExpression property,
            @JsonProperty("range") @Nonnull OWLDataRange range,
            @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Set<? extends OWLAnnotation> annotations) {
    }
}
