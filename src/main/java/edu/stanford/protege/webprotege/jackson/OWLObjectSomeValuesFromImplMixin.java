package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("ObjectSomeValuesFrom")
public class OWLObjectSomeValuesFromImplMixin {

    @JsonCreator
    public OWLObjectSomeValuesFromImplMixin(@JsonProperty("property") @Nonnull OWLObjectPropertyExpression property,
                                            @JsonProperty("filler") @Nonnull OWLClassExpression filler) {
    }
}
