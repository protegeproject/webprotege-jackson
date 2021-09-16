package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.semanticweb.owlapi.model.IRI;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-05
 */
public abstract class IRIMixin {

    @JsonCreator
    protected IRIMixin(@Nonnull String s) {

    }

    @JsonValue
    @Override
    public abstract String toString();
}
