package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.semanticweb.owlapi.model.*;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
public class OWLAnnotationValueSerializationConverter extends StdConverter<OWLAnnotationValue, OWLAnnotationValueProxy> {

    @Override
    public OWLAnnotationValueProxy convert(OWLAnnotationValue value) {
        return value.accept(new OWLAnnotationValueVisitorEx<OWLAnnotationValueProxy>() {
            @Nonnull
            @Override
            public OWLAnnotationValueProxy visit(@Nonnull IRI iri) {
                return new OWLAnnotationValueProxy(iri.toString(), null, null, null, null);
            }

            @Nonnull
            @Override
            public OWLAnnotationValueProxy visit(@Nonnull OWLAnonymousIndividual individual) {
                return new OWLAnnotationValueProxy(null, individual.getID().getID(), null, null, null);
            }

            @Nonnull
            @Override
            public OWLAnnotationValueProxy visit(@Nonnull OWLLiteral literal) {
                String datatypeString = null;
                if(!literal.isRDFPlainLiteral()) {
                    datatypeString = literal.getDatatype().getIRI().toString();
                }
                return new OWLAnnotationValueProxy(null, null, literal.getLiteral(), literal.getLang(), datatypeString);
            }
        });
    }
}
