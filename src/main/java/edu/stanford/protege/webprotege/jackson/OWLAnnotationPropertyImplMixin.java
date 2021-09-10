package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.IRI;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonTypeName("AnnotationProperty")
public class OWLAnnotationPropertyImplMixin {

    @JsonCreator
    public OWLAnnotationPropertyImplMixin(@JsonProperty("iri") IRI iri) {
    }
}
