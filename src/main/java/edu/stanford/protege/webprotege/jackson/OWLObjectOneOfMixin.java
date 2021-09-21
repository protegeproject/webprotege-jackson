package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectIntersectionOfImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectOneOfImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonTypeName("ObjectOneOf")
@JsonDeserialize(as = OWLObjectOneOfImpl.class)
@JsonIncludeProperties({"individuals"})
public interface OWLObjectOneOfMixin {

}
