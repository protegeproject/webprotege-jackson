package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import uk.ac.manchester.cs.owl.owlapi.OWLDataPropertyDomainAxiomImpl;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonTypeName("DataPropertyDomain")
@JsonDeserialize(as = OWLDataPropertyDomainAxiomImpl.class)
@JsonIncludeProperties({"property", "domain", "annotations"})
public class OWLDataPropertyDomainAxiomMixin {

}
