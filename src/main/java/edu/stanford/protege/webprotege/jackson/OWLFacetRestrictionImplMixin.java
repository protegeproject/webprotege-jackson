package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.vocab.OWLFacet;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
public class OWLFacetRestrictionImplMixin {

    @JsonCreator
    public OWLFacetRestrictionImplMixin(@JsonProperty("facet")OWLFacet facet,
                                        @JsonProperty("value")OWLLiteral value) {
    }
}
