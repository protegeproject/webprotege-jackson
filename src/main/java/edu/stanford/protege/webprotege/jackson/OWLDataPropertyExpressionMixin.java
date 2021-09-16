package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonDeserialize(as = OWLDataPropertyImpl.class)
public class OWLDataPropertyExpressionMixin {

}
