package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLDatatype;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
public interface OWLLiteralMixin {

    @JsonProperty("value")
    @JsonAlias("literal")
    String getLiteral();

    @JsonProperty("datatype")
    @JsonAlias("type")
    OWLDatatype getDatatype();

}
