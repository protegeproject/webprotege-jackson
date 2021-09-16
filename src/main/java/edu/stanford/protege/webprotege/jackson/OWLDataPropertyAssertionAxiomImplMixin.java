package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonTypeName("DataPropertyAssertion")
public class OWLDataPropertyAssertionAxiomImplMixin {

    @JsonCreator
    public OWLDataPropertyAssertionAxiomImplMixin(@JsonProperty("subject") @Nonnull OWLIndividual subject,
                                                  @JsonProperty("property") @Nonnull OWLDataPropertyExpression property,
                                                  @JsonProperty("object") @Nonnull OWLLiteral value,
                                                  @JsonProperty("annotations") @JsonSetter(nulls = Nulls.AS_EMPTY)  @Nonnull Set<? extends OWLAnnotation> annotations) {
    }
}
