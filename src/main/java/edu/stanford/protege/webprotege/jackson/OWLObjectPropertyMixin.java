package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.semanticweb.owlapi.model.IRI;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectPropertyImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonIncludeProperties("iri")
@JsonDeserialize(as = OWLObjectPropertyImpl.class)
@JsonTypeName("ObjectProperty")
public abstract class OWLObjectPropertyMixin {

}
