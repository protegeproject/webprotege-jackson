package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
public record OWLAnnotationSubjectProxy(@JsonInclude(NON_EMPTY) String iri, @JsonInclude(NON_EMPTY) String nodeId) {

}
