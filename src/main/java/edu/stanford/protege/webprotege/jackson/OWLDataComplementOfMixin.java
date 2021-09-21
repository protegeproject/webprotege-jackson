package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLDataRange;
import uk.ac.manchester.cs.owl.owlapi.OWLDataComplementOfImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonTypeName("DataComplementOf")
@JsonDeserialize(as = OWLDataComplementOfImpl.class)
@JsonIncludeProperties("dataRange")
public interface OWLDataComplementOfMixin {

    @JsonProperty("dataRange")
    OWLDataRange getOperand();
}
