package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-04
 */
@JsonSubTypes({
        @JsonSubTypes.Type(OWLNamedIndividual.class),
        @JsonSubTypes.Type(OWLAnonymousIndividual.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true)
public class OWLIndividualMixin {

}
