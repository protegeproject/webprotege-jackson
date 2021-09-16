package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.semanticweb.owlapi.model.OWLLiteral;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
public record OWLAnnotationValueProxy(@JsonInclude(NON_EMPTY) String iri, @JsonInclude(NON_EMPTY) String nodeId, @JsonInclude(NON_EMPTY) @JsonUnwrapped OWLLiteral literal) {

}
