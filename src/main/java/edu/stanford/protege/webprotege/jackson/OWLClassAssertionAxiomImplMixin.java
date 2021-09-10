package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLIndividual;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-04
 */
@JsonTypeName("ClassAssertion")
public class OWLClassAssertionAxiomImplMixin {

    @JsonCreator
    public OWLClassAssertionAxiomImplMixin(@JsonProperty(value = "individual", required = true) @Nonnull OWLIndividual individual,
                                           @JsonProperty(value = "classExpression", required = true) @Nonnull OWLClassExpression classExpression,
                                           @JsonProperty(value = "annotations", defaultValue = "[]") @Nonnull Collection<? extends OWLAnnotation> annotations) {
    }
}
