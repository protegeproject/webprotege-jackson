package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-12
 */
@JsonIncludeProperties({"property", "value", "annotations"})
@JsonDeserialize(as = OWLAnnotationImpl.class)
public interface OWLAnnotationMixin {

    @JsonProperty("property")
    @JsonSerialize(converter = OWLEntityIriToStringConverter.class)
    @JsonDeserialize(converter = StringToOWLAnnotationPropertyConverter.class)
    OWLAnnotationProperty getProperty();

    @JsonProperty("value")
    @JsonSerialize(converter = OWLAnnotationValueSerializationConverter.class)
    @JsonDeserialize(converter = OWLAnnotationValueDeserializationConverter.class)
    OWLAnnotationValue getValue();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Set<OWLAnnotation> getAnnotations();
}
