package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.semanticweb.owlapi.model.OWLEntity;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-12
 */
public class OWLEntityAsIriStringSerializer extends StdSerializer<Object> {

    public OWLEntityAsIriStringSerializer() {
        super(Object.class);
    }

    @Override
    public void serialize(Object value, JsonGenerator gen,
                          SerializerProvider provider) throws IOException {
        gen.writeString(((OWLEntity) value).getIRI().toString());
    }

    // IMPORTANT: copied from `NonTypedScalarSerializerBase`
    @Override
    public void serializeWithType(Object value, JsonGenerator gen,
                                  SerializerProvider provider, TypeSerializer typeSer)
            throws IOException {
        // no type info, just regular serialization
        serialize(value, gen, provider);
    }
}
