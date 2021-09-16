package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.NodeID;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import uk.ac.manchester.cs.owl.owlapi.OWLAnonymousIndividualImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
public class OWLAnnotationValueDeserializationConverter extends StdConverter<OWLAnnotationValueProxy, OWLAnnotationValue> {

    @Override
    public OWLAnnotationValue convert(OWLAnnotationValueProxy value) {
        if(value.iri() != null) {
            return IRI.create(value.iri());
        }
        else if(value.nodeId() != null) {
            return new OWLAnonymousIndividualImpl(NodeID.getNodeID(value.nodeId()));
        }
        else if(value.literal() != null) {
            return value.literal();
        }
        else {
            throw new RuntimeException();
        }
    }
}
