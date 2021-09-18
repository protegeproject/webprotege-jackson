package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLClassExpression;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectSomeValuesFromImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonTypeName("ObjectSomeValuesFrom")
@JsonIncludeProperties({"property", "filler"})
@JsonDeserialize(as = OWLObjectSomeValuesFromImpl.class)
public interface OWLObjectSomeValuesFromMixin {

}
