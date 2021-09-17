package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("NegativeObjectPropertyAssertion")
public class OWLNegativeObjectPropertyAssertionAxiomImplMixin {

    @JsonCreator
    public OWLNegativeObjectPropertyAssertionAxiomImplMixin(@JsonProperty("subject") @Nonnull OWLIndividual subject,
                                                            @JsonProperty("property") @Nonnull OWLObjectPropertyExpression property,
                                                            @JsonProperty("object") @Nonnull OWLIndividual object,
                                                            @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Set<? extends OWLAnnotation> annotations) {
    }
}
