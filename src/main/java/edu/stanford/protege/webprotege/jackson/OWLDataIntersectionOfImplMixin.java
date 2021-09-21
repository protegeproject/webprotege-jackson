package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLDataRange;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonTypeName("DataIntersectionOf")
public class OWLDataIntersectionOfImplMixin {

    @JsonCreator
    public OWLDataIntersectionOfImplMixin(@JsonProperty("dataRanges") Set<OWLDataRange> dataRanges) {
    }
}
