package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-12-04
 */

public class OWLLiteralSerializer extends StdSerializer<OWLLiteral> {

    public OWLLiteralSerializer() {
        super(OWLLiteral.class);
    }

    @Override
    public void serialize(OWLLiteral value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        if(value.isRDFPlainLiteral()) {
            gen.writeFieldName("lang");
            gen.writeString(value.getLang());
        }
        else {
            gen.writeFieldName("datatype");
            gen.writeObject(value.getDatatype().getIRI());
        }
        gen.writeFieldName("value");
        gen.writeObject(value.getLiteral());
        gen.writeEndObject();
    }

    @Override
    public void serializeWithType(OWLLiteral value,
                                  JsonGenerator gen,
                                  SerializerProvider serializers,
                                  TypeSerializer typeSer) throws IOException {
        this.serialize(value, gen, serializers);
    }
}
