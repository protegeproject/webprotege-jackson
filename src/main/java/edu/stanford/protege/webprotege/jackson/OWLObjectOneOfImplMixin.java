package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.semanticweb.owlapi.model.OWLIndividual;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonTypeName("ObjectOneOf")
public class OWLObjectOneOfImplMixin {

    @JsonCreator
    public OWLObjectOneOfImplMixin(@JsonProperty("individuals") @Nonnull Set<? extends OWLIndividual> values) {
    }
}
