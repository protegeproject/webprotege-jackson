package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.vocab.OWLFacet;
import uk.ac.manchester.cs.owl.owlapi.OWLFacetRestrictionImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonDeserialize(as = OWLFacetRestrictionImpl.class)
@JsonIncludeProperties({"facet", "value"})
public interface OWLFacetRestrictionMixin {

    @JsonSerialize(converter = OWLFacetToIriConverter.class)
    @JsonDeserialize(converter = OWLFacetFromIriConverter.class)
    OWLFacet getFacet();

    @JsonProperty("value")
    OWLLiteral getFacetValue();
}
