package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.semanticweb.owlapi.model.OWLEntity;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 23 Apr 2018
 */
@JsonComponent
public class OWLEntitySerializer extends StdSerializer<OWLEntity> {

    public static final String TYPE_FIELD_NAME = "@type";

    public static final String IRI_FIELD_NAME = "iri";

    public OWLEntitySerializer() {
        super(OWLEntity.class);
    }

    @Override
    public void serialize(OWLEntity entity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName(TYPE_FIELD_NAME);
        jsonGenerator.writeObject(entity.getEntityType());
        jsonGenerator.writeFieldName(IRI_FIELD_NAME);
        jsonGenerator.writeObject(entity.getIRI());
        jsonGenerator.writeEndObject();
    }
}
