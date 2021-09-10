package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-04
 */
public abstract class NodeIDMixin {

    @JsonCreator
    public NodeIDMixin(String id) {
    }

    @JsonValue
    public abstract String getID();
}
