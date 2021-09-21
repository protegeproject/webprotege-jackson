package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.databind.util.StdConverter;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.vocab.OWLFacet;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
public class OWLFacetFromIriConverter extends StdConverter<IRI, OWLFacet> {

    @Override
    public OWLFacet convert(IRI value) {
        return OWLFacet.getFacet(value);
    }
}
