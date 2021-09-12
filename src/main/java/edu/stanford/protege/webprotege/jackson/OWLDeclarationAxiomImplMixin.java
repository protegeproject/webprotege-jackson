package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLEntity;

import java.util.Collection;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonTypeName("Declaration")
public class OWLDeclarationAxiomImplMixin {

    @JsonCreator
    public OWLDeclarationAxiomImplMixin(@JsonProperty("entity") OWLEntity entity,
                                        @JsonProperty("annotations") @JsonSetter(nulls = Nulls.AS_EMPTY) Collection<? extends OWLAnnotation> annotations) {
    }
}
