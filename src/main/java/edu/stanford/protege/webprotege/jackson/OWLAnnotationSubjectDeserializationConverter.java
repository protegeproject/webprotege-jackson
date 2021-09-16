package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.NodeID;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import uk.ac.manchester.cs.owl.owlapi.OWLAnonymousIndividualImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
public class OWLAnnotationSubjectDeserializationConverter extends StdConverter<OWLAnnotationSubjectProxy, OWLAnnotationSubject> {

    @Override
    public OWLAnnotationSubject convert(OWLAnnotationSubjectProxy value) {
        if(value.iri() != null) {
            return IRI.create(value.iri());
        }
        else {
            return new OWLAnonymousIndividualImpl(NodeID.getNodeID(value.nodeId()));
        }
    }
}
