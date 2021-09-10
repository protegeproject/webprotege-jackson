package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.semanticweb.binaryowl.BinaryOWLVersion;
import org.semanticweb.binaryowl.owlobject.OWLObjectBinaryType;
import org.semanticweb.binaryowl.stream.BinaryOWLOutputStream;
import org.semanticweb.owlapi.functional.renderer.FunctionalSyntaxObjectRenderer;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntologyID;
import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLOntologyImpl;
import uk.ac.manchester.cs.owl.owlapi.OWLOntologyManagerImpl;
import uk.ac.manchester.cs.owl.owlapi.concurrent.NoOpReadWriteLock;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-02
 */
public class OWLAxiomSerializer extends StdSerializer<OWLAxiom> {

    public OWLAxiomSerializer() {
        super(OWLAxiom.class);
    }


    @Override
    public void serialize(OWLAxiom owlAxiom,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        var dataOutput = new ByteArrayOutputStream();
        BinaryOWLOutputStream bos = new BinaryOWLOutputStream(dataOutput, BinaryOWLVersion.getVersion(2));
        OWLObjectBinaryType.write(owlAxiom, bos);
        var encoded = Base64.getEncoder().encodeToString(dataOutput.toByteArray());
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("axiom", encoded);
        jsonGenerator.writeEndObject();
    }
}
