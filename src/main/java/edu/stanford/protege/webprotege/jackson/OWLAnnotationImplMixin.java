package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import uk.ac.manchester.cs.owl.owlapi.OWLAnnotationImpl;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-12
 */
public abstract class OWLAnnotationImplMixin {


    @JsonCreator
    public OWLAnnotationImplMixin(@JsonProperty("property") @JsonDeserialize(using = OWLAnnotationPropertyAsIriStringDeserializer.class) @Nonnull OWLAnnotationProperty property,
                              @JsonProperty("value") @Nonnull OWLAnnotationValue value,
                              @JsonProperty(value = "annotations", defaultValue = "[]") @JsonSetter(nulls = Nulls.AS_EMPTY)  @Nonnull Set<? extends OWLAnnotation> annotations) {
    }

    @JsonProperty("property")
    @JsonDeserialize(using = OWLAnnotationPropertyAsIriStringDeserializer.class)
    public abstract OWLAnnotationProperty getProperty();


}
