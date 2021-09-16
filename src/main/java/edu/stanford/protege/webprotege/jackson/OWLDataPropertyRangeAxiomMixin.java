package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyRangeAxiomImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonTypeName("DataPropertyRange")
@JsonDeserialize(as = OWLDataPropertyRangeAxiomImpl.class)
@JsonIncludeProperties({"property", "range", "annotations"})
public class OWLDataPropertyRangeAxiomMixin {

}
