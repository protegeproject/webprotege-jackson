package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.semanticweb.owlapi.model.OWLAnnotation;
import uk.ac.manchester.cs.owl.owlapi.OWLEquivalentClassesAxiomImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonSubTypes({
        @JsonSubTypes.Type(OWLEquivalentClassesAxiomImpl.class)
})
@JsonIncludeProperties({"classExpressions", "annotations"})
public abstract class OWLEquivalentClassesAxiomMixin {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public abstract Set<OWLAnnotation> getAnnotations();
}
