package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("EquivalentObjectProperties")
public class OWLEquivalentObjectPropertiesAxiomImplMixin {

    @JsonCreator
    public OWLEquivalentObjectPropertiesAxiomImplMixin(@JsonProperty("properties") @Nonnull Set<? extends OWLObjectPropertyExpression> properties,
                                                       @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Collection<? extends OWLAnnotation> annotations) {
    }
}
