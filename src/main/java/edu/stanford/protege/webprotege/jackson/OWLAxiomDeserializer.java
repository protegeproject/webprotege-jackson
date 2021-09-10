package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.semanticweb.binaryowl.BinaryOWLVersion;
import org.semanticweb.binaryowl.owlobject.OWLObjectBinaryType;
import org.semanticweb.binaryowl.stream.BinaryOWLInputStream;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.Base64;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
public class OWLAxiomDeserializer extends StdDeserializer<OWLAxiom> {

    private final OWLDataFactory dataFactory;

    public OWLAxiomDeserializer(OWLDataFactory dataFactory) {
        super(OWLAxiom.class);
        this.dataFactory = dataFactory;
    }

    @Override
    public OWLAxiom deserialize(JsonParser jsonParser,
                                DeserializationContext deserializationContext) throws IOException {
        jsonParser.nextToken();
        jsonParser.nextFieldName();
        var axiomSerialization = jsonParser.readValueAs(String.class);
        var bytes = Base64.getDecoder().decode(axiomSerialization);
        var axiom = (OWLAxiom) OWLObjectBinaryType.read(new BinaryOWLInputStream(new ByteArrayInputStream(bytes),
                                                                                 dataFactory,
                                                          BinaryOWLVersion.getVersion(2)));
        jsonParser.nextToken();
        return axiom;
    }
}
