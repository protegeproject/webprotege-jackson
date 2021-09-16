package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonTypeName("DisjointDataProperties")
public class OWLDisjointDataPropertiesAxiomImplMixin {

    @JsonCreator
    public OWLDisjointDataPropertiesAxiomImplMixin(@JsonProperty("properties") @Nonnull Set<? extends OWLDataPropertyExpression> properties,
                                                   @JsonProperty(value = "annotations", defaultValue = "[]")  @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Collection<? extends OWLAnnotation> annotations) {
    }
}
