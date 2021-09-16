package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import uk.ac.manchester.cs.owl.owlapi.OWLSubObjectPropertyOfAxiomImpl;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonTypeName("SubObjectPropertyOf")
@JsonIncludeProperties({"subProperty", "superProperty", "annotations"})
@JsonDeserialize(as = OWLSubObjectPropertyOfAxiomImpl.class)
public abstract class OWLSubObjectPropertyOfAxiomMixin {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public abstract Set<OWLAnnotation> getAnnotations();
}
