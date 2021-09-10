package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-04
 */
@JsonSubTypes({
        @JsonSubTypes.Type(OWLAnonymousIndividual.class),
        @JsonSubTypes.Type(IRI.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class OWLAnnotationSubjectMixin {

}
