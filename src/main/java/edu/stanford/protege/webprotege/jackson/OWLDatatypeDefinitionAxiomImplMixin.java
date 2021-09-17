package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDatatype;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTypeName("DatatypeDefinition")
public class OWLDatatypeDefinitionAxiomImplMixin {

    @JsonCreator
    public OWLDatatypeDefinitionAxiomImplMixin(@JsonProperty("datatype") @Nonnull OWLDatatype datatype,
                                               @JsonProperty("dataRange") @Nonnull OWLDataRange dataRange,
                                               @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY) @Nonnull Collection<? extends OWLAnnotation> annotations) {
    }
}
