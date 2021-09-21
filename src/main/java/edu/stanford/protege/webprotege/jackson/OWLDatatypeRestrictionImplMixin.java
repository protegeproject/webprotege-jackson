package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLFacetRestriction;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonTypeName("DatatypeRestriction")
public class OWLDatatypeRestrictionImplMixin {

    @JsonCreator
    public OWLDatatypeRestrictionImplMixin(@JsonProperty("datatype") OWLDatatype datatype,
                                           @JsonProperty("facetRestrictions") Set<? extends OWLFacetRestriction> facetRestrictions) {
    }
}
