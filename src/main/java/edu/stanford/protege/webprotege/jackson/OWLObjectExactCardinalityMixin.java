package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectExactCardinalityImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("ObjectExactCardinality")
@JsonDeserialize(as = OWLObjectExactCardinalityImpl.class)
@JsonIncludeProperties({"cardinality", "property", "filler"})
public class OWLObjectExactCardinalityMixin {

}
