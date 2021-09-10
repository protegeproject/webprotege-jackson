package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.NodeID;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-04
 */
@JsonTypeName("AnonymousIndividual")
public class OWLAnonymousIndividualImplMixin {

    public OWLAnonymousIndividualImplMixin(@JsonProperty(value = "id", required = true) NodeID id) {
    }
}
