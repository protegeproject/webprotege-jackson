package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.vocab.OWLFacet;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
public class OWLFacetToIriConverter extends StdConverter<OWLFacet, IRI> {

    @Override
    public IRI convert(OWLFacet value) {
        return value.getIRI();
    }
}
