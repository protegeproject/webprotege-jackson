package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.semanticweb.owlapi.model.*;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonSubTypes({
        @Type(OWLClass.class),
        @Type(OWLDatatype.class),
        @Type(OWLObjectProperty.class),
        @Type(OWLDataProperty.class),
        @Type(OWLAnnotationProperty.class),
        @Type(OWLNamedIndividual.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true)
@JsonIncludeProperties("iri")
public abstract class OWLEntityMixin {

    @JsonProperty("iri")
    public abstract IRI getIRI();
}
