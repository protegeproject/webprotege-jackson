package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.*;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("DataHasValue")
public abstract class OWLDataHasValueImplMixin {

    @JsonCreator
    public OWLDataHasValueImplMixin(@JsonProperty("property") @Nonnull OWLDataPropertyExpression property,
                                    @JsonProperty("filler") @Nonnull OWLLiteral filler) {
    }

    @JsonProperty("filler")
    public abstract OWLLiteral getValue();
}
