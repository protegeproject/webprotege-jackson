package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLDataRange;
import uk.ac.manchester.cs.owl.owlapi.OWLDataIntersectionOfImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDataUnionOfImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonTypeName("DataUnionOf")
@JsonDeserialize(as = OWLDataUnionOfImpl.class)
@JsonIncludeProperties("dataRanges")
public interface OWLDataUnionOfMixin {

    @JsonProperty("dataRanges")
    Set<OWLDataRange> getOperands();
}
