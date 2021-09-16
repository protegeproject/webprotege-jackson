package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.semanticweb.owlapi.model.OWLDatatype;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonSubTypes({
        @Type(OWLDatatype.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class OWLDataRangeMixin {

}
