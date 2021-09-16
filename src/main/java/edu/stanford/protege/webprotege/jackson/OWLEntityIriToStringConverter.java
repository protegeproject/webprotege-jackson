package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.databind.util.StdConverter;
import org.semanticweb.owlapi.model.OWLEntity;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-12
 */
public class OWLEntityIriToStringConverter extends StdConverter<OWLEntity, String> {

    @Override
    public String convert(OWLEntity value) {
        return value.getIRI().toString();
    }
}
