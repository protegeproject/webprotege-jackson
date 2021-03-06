package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import org.semanticweb.owlapi.model.OWLAnnotation;
import uk.ac.manchester.cs.owl.owlapi.OWLDeclarationAxiomImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonSubTypes({@Type(value = OWLDeclarationAxiomImpl.class)})
@JsonIncludeProperties({"entity", "annotations"})
public abstract class OWLDeclarationAxiomMixin {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public abstract Set<OWLAnnotation> getAnnotations();
}