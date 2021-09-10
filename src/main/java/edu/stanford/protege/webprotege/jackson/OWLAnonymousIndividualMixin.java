package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.semanticweb.owlapi.model.NodeID;
import uk.ac.manchester.cs.owl.owlapi.OWLAnonymousIndividualImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-04
 */
@JsonSubTypes({
        @JsonSubTypes.Type(OWLAnonymousIndividualImpl.class)
})
@JsonIncludeProperties("id")
public abstract class OWLAnonymousIndividualMixin {

    @JsonProperty("id")
    public abstract NodeID getID();
}
