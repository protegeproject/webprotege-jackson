package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.ac.manchester.cs.owl.owlapi.OWLObjectMinCardinalityImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("ObjectMinCardinality")
@JsonDeserialize(as = OWLObjectMinCardinalityImpl.class)
@JsonIncludeProperties({"cardinality", "property", "filler"})
public class OWLObjectMinCardinalityMixin {

}
