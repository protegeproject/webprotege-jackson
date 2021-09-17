package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import uk.ac.manchester.cs.owl.owlapi.OWLInverseObjectPropertiesAxiomImpl;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("InverseObjectProperties")
public class OWLInverseObjectPropertiesAxiomImplMixin {

    @JsonCreator
    public OWLInverseObjectPropertiesAxiomImplMixin(@JsonProperty("firstProperty") @Nonnull OWLObjectPropertyExpression first,
                                                    @JsonProperty("secondProperty") @Nonnull OWLObjectPropertyExpression second,
                                                    @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Collection<? extends OWLAnnotation> annotations) {
    }
}
