package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import edu.stanford.protege.webprotege.jackson.OWLLiteralDeserializer;
import org.semanticweb.owlapi.model.OWLAnnotationValue;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

public class OWLAnnotationValueDeserializer extends StdDeserializer<OWLAnnotationValue> {

    @Nonnull
    private final OWLLiteralDeserializer literalDeserializer;

    @Nonnull
    private final IriDeserializer iriDeserializer;

    @Inject
    public OWLAnnotationValueDeserializer(@Nonnull OWLLiteralDeserializer literalDeserializer, @Nonnull IriDeserializer iriDeserializer) {
        super(OWLAnnotationValue.class);
        this.literalDeserializer = checkNotNull(literalDeserializer);
        this.iriDeserializer = checkNotNull(iriDeserializer);
    }

    @Override
    public OWLAnnotationValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if(p.hasToken(JsonToken.START_OBJECT)) {
            return literalDeserializer.deserialize(p, ctxt);
        }
        else {
            return iriDeserializer.deserialize(p, ctxt);
        }
    }
}

