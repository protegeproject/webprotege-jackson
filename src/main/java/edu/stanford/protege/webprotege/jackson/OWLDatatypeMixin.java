package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import uk.ac.manchester.cs.owl.owlapi.OWLDatatypeImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonSubTypes({
        @JsonSubTypes.Type(OWLDatatypeImpl.class)
})
@JsonIncludeProperties("iri")
public class OWLDatatypeMixin {

}
