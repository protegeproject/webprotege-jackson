package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
//@JsonSubTypes({
//        @Type(value = OWLClassImpl.class)
//})
@JsonTypeName("Class")
//@JsonIncludeProperties("iri")
public abstract class OWLClassMixin implements OWLClassExpressionMixin {

//    @JsonProperty("iri")
//    public abstract IRI getIRI();
}
