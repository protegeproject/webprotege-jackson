package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLDataRange;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonTypeName("DataComplementOf")
public class OWLDataComplementOfImplMixin {

    @JsonCreator
    public OWLDataComplementOfImplMixin(@JsonProperty("dataRange") OWLDataRange dataRange) {
    }
}
