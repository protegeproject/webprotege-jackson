package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyAssertionAxiomImpl;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-13
 */
@JsonIncludeProperties({"property", "subject", "object", "annotations"})
@JsonDeserialize(as = OWLObjectPropertyAssertionAxiomImpl.class)
@JsonTypeName("ObjectPropertyAssertion")
public abstract class OWLObjectPropertyAssertionAxiomMixin {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty("annotations")
    public abstract Set<OWLAnnotation> getAnnotations();
}
