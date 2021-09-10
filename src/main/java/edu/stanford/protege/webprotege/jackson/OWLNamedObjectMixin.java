package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.semanticweb.owlapi.model.IRI;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-05
 */
public abstract class OWLNamedObjectMixin {

    @JsonProperty("iri")
    @JsonSerialize(using = IriStringSerializer.class)
    public abstract IRI getIRI();
}
