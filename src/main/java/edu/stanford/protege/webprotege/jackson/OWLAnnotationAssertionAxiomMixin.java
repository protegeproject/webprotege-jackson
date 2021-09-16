package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationAssertionAxiomImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonSubTypes({@Type(value = OWLAnnotationAssertionAxiomImpl.class)})
@JsonIncludeProperties({"subject", "property", "value", "annotations"})
public abstract class OWLAnnotationAssertionAxiomMixin {

    @JsonSerialize(converter = OWLAnnotationSubjectSerializationConverter.class)
    @JsonDeserialize(converter = OWLAnnotationSubjectDeserializationConverter.class)
    public abstract OWLAnnotationSubject getSubject();

    @JsonSerialize(converter = OWLAnnotationValueSerializationConverter.class)
    @JsonDeserialize(converter = OWLAnnotationValueDeserializationConverter.class)
    public abstract OWLAnnotationSubject getValue();

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public abstract Set<OWLAnnotation> getAnnotations();
}