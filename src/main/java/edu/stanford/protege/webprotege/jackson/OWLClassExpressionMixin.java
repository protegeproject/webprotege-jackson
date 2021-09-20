package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.semanticweb.owlapi.model.*;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
@JsonSubTypes({
        @Type(OWLClass.class),
        @Type(OWLObjectSomeValuesFrom.class),
        @Type(OWLObjectAllValuesFrom.class),
        @Type(OWLObjectMinCardinality.class),
        @Type(OWLObjectMaxCardinality.class),
        @Type(OWLObjectExactCardinality.class),
        @Type(OWLObjectHasValue.class),
        @Type(OWLObjectHasSelf.class),
        @Type(OWLObjectIntersectionOf.class),
        @Type(OWLObjectUnionOf.class),
        @Type(OWLObjectComplementOf.class),
        @Type(OWLDataSomeValuesFrom.class),
        @Type(OWLDataAllValuesFrom.class),
        @Type(OWLDataHasValue.class),
        @Type(OWLDataMinCardinality.class),
        @Type(OWLDataMaxCardinality.class),
        @Type(OWLDataExactCardinality.class)
})
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, visible = true)
public interface OWLClassExpressionMixin {

}
