package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.semanticweb.owlapi.model.IRI;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 18 Jun 2018
 */

public class IriSerializer extends StdSerializer<IRI> {

    public IriSerializer() {
        super(IRI.class);
    }

    @Override
    public void serialize(IRI iri, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(iri.toString());
    }

    @Override
    public void serializeWithType(IRI value,
                                  JsonGenerator gen,
                                  SerializerProvider serializers,
                                  TypeSerializer typeSer) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("@type", "IRI");
        gen.writeStringField("value", value.toString());
        gen.writeEndObject();
    }
}
