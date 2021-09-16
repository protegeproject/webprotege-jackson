package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import org.semanticweb.owlapi.model.IRI;

import javax.annotation.Nullable;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonIncludeProperties({"iri", "versionIri"})
public abstract class OWLOntologyIDMixin {

    @JsonCreator
    public OWLOntologyIDMixin(@Nullable @JsonProperty("iri") IRI iri, @Nullable @JsonProperty("versionIri") IRI versionIRI) {

    }

    @JsonProperty("iri")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public abstract Optional<IRI> getOntologyIRI();

    @JsonProperty("versionIri")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    public abstract Optional<IRI> getVersionIRI();

}
