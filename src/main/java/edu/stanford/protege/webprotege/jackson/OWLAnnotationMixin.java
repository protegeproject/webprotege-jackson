package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-12
 */
@JsonIncludeProperties({"property", "value", "annotations"})
@JsonDeserialize(as = OWLAnnotationImpl.class)
public abstract class OWLAnnotationMixin {

    @JsonProperty("property")
    @JsonSerialize(using = OWLEntityAsIriStringSerializer.class)
    @JsonDeserialize(using = OWLAnnotationPropertyAsIriStringDeserializer.class)
    public abstract OWLAnnotationProperty getProperty();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public abstract Set<OWLAnnotation> getAnnotations();
}
