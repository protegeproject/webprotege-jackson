package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonTypeName("SymmetricObjectProperty")
public class OWLSymmetricObjectPropertyAxiomImplMixin {

    @JsonCreator
    public OWLSymmetricObjectPropertyAxiomImplMixin(@JsonProperty("property") @Nonnull OWLObjectPropertyExpression property,
                                                    @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Collection<? extends OWLAnnotation> annotations) {
    }
}
