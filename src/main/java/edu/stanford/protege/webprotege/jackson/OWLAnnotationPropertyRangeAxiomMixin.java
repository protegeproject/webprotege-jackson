package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationPropertyRangeAxiomImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("AnnotationPropertyRange")
@JsonDeserialize(as = OWLAnnotationPropertyRangeAxiomImpl.class)
@JsonIncludeProperties({"property", "range", "annotations"})
public interface OWLAnnotationPropertyRangeAxiomMixin {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Set<OWLAnnotation> getAnnotations();
}
