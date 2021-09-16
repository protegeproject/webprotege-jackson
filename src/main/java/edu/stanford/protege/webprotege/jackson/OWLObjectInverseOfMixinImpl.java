package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-13
 */
@JsonTypeName("ObjectInverseOf")
public abstract class OWLObjectInverseOfMixinImpl {

    @JsonCreator
    public OWLObjectInverseOfMixinImpl(
            @Nonnull OWLObjectPropertyExpression inverseProperty) {
    }
}
