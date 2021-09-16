package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLAnnotationSubjectVisitorEx;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
public class OWLAnnotationSubjectSerializationConverter extends StdConverter<OWLAnnotationSubject, OWLAnnotationSubjectProxy> {

    @Override
    public OWLAnnotationSubjectProxy convert(OWLAnnotationSubject value) {
        return value.accept(new OWLAnnotationSubjectVisitorEx<>() {
            @Nonnull
            @Override
            public OWLAnnotationSubjectProxy visit(@Nonnull IRI iri) {
                return new OWLAnnotationSubjectProxy(iri.toString(), null);
            }

            @Nonnull
            @Override
            public OWLAnnotationSubjectProxy visit(@Nonnull OWLAnonymousIndividual individual) {
                return new OWLAnnotationSubjectProxy(null, individual.getID().getID());
            }
        });
    }
}
