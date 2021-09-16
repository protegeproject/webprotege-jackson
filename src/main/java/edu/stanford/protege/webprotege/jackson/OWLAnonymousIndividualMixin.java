package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.NodeID;
import uk.ac.manchester.cs.owl.owlapi.OWLAnonymousIndividualImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-04
 */
@JsonIncludeProperties("id")
@JsonDeserialize(as = OWLAnonymousIndividualImpl.class)
@JsonTypeName("AnonymousIndividual")
public abstract class OWLAnonymousIndividualMixin {

    @JsonProperty("id")
    public abstract NodeID getID();
}
