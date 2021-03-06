package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLProperty;
import org.springframework.boot.jackson.JsonComponent;

import javax.annotation.Nonnull;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-12-04
 */

public class OWLPropertyDeserializer<P extends OWLProperty> extends StdDeserializer<P> {

    @Nonnull
    private final OWLEntityDeserializer<P> deserializer;

    public OWLPropertyDeserializer(@Nonnull OWLDataFactory dataFactory) {
        super(OWLProperty.class);
        deserializer = new OWLEntityDeserializer<>(dataFactory);
    }

    @Override
    public P deserialize(JsonParser jsonParser,
                                   DeserializationContext deserializationContext) throws IOException {
        return (P) deserializer.deserialize(jsonParser, deserializationContext);
    }
}
