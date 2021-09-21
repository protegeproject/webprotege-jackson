package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLDataIntersectionOf;
import org.semanticweb.owlapi.model.OWLDataRange;
import uk.ac.manchester.cs.owl.owlapi.OWLDataIntersectionOfImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonTypeName("DataIntersectionOf")
@JsonDeserialize(as = OWLDataIntersectionOfImpl.class)
@JsonIncludeProperties("dataRanges")
public interface OWLDataIntersectionOfMixin {

    @JsonProperty("dataRanges")
    Set<OWLDataRange> getOperands();
}
