package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import uk.ac.manchester.cs.owl.owlapi.OWLDisjointClassesAxiomImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLDisjointUnionAxiomImpl;

import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("DisjointUnion")
@JsonDeserialize(as = OWLDisjointUnionAxiomImpl.class)
@JsonIncludeProperties({"class", "classExpressions", "annotations"})
public interface OWLDisjointUnionAxiomMixin {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Set<OWLAnnotation> getAnnotations();

    @JsonProperty("class")
    OWLClass getOWLClass();
}
