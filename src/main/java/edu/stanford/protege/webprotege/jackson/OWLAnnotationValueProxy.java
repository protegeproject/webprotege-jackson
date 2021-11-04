package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.semanticweb.owlapi.model.*;
import uk.ac.manchester.cs.owl.owlapi.OWLAnonymousIndividualImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDatatypeImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLLiteralImpl;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
public record OWLAnnotationValueProxy(@JsonInclude(NON_EMPTY) String iri,
                                      @JsonInclude(NON_EMPTY) String nodeId,
                                      @JsonInclude(NON_EMPTY) String value,
                                      @JsonInclude(NON_EMPTY) String lang,
                                      @JsonInclude(NON_EMPTY) String datatype) {

    public OWLAnnotationValue toAnnotationValue() {
        if(iri != null) {
            return IRI.create(iri);
        }
        else if(nodeId != null) {
            return new OWLAnonymousIndividualImpl(NodeID.getNodeID(nodeId));
        }
        else if(value != null) {
            OWLDatatype dt = null;
            if(this.datatype != null) {
                dt = new OWLDatatypeImpl(IRI.create(this.datatype()));
            }
            return new OWLLiteralImpl(this.value, this.lang, dt);
        }
        else {
            throw new RuntimeException("Expected iri, nodeId or value");
        }
    }
}
