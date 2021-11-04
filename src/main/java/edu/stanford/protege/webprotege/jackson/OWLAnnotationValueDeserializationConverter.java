package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.NodeID;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import org.semanticweb.owlapi.model.OWLDatatype;
import uk.ac.manchester.cs.owl.owlapi.OWLAnonymousIndividualImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDatatypeImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLLiteralImpl;

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
        else if(value.value() != null) {
            OWLDatatype datatype = null;
            if(value.datatype() != null) {
                datatype = new OWLDatatypeImpl(IRI.create(value.datatype()));
            }
            return new OWLLiteralImpl(value.value(), value.lang(), datatype);
        }
        else {
            throw new RuntimeException("Expected iri, nodeId or value");
        }
    }
}
