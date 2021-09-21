package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLLiteral;
import uk.ac.manchester.cs.owl.owlapi.OWLDataOneOfImpl;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonTypeName("DataOneOf")
@JsonDeserialize(as = OWLDataOneOfImpl.class)
@JsonIncludeProperties("literals")
public interface OWLDataOneOfMixin {

    @JsonProperty("literals")
    Set<OWLLiteral> getValues();
}
