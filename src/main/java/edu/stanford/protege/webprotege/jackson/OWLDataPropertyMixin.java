package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonSubTypes({
        @Type(OWLDataPropertyImpl.class)
})
@JsonIncludeProperties("iri")
public class OWLDataPropertyMixin {

}
