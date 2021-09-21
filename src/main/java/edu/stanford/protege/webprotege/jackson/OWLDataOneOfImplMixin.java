package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLLiteral;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonTypeName("DataOneOf")
public class OWLDataOneOfImplMixin {

    @JsonCreator
    public OWLDataOneOfImplMixin(@JsonProperty("literals") Set<? extends OWLLiteral> literals) {
    }
}
