package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class OWLLiteral_Serialization_TestCase {

    @Autowired
    private OWLDataFactory dataFactory;

    @Autowired
    private JacksonTester<OWLLiteral> tester;

    @Autowired
    private JacksonTester<OWLAnnotationValue> annotationValueTester;

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void shouldWrite_OWLLiteral_with_LangTag() throws IOException {
        var literal = dataFactory.getOWLLiteral("Hello", "en");
        var json = tester.write(literal);
        assertThat(json).hasJsonPathValue("value", "Hello");
        assertThat(json).hasJsonPathValue("lang", "en");
    }

    @Test
    public void shouldWrite_OWLLiteral_with_Datatype() throws IOException {
        var literal = dataFactory.getOWLLiteral(33);
        var json = tester.write(literal);
        assertThat(json).hasJsonPathValue("value", "33");
        assertThat(json).hasJsonPathValue("['@type']", "xsd:integer");
    }


    @Test
    public void shouldRead_OWLLiteral_with_Datatype() throws IOException {
        var json = """
                {
                    "value" : 33,
                    "type"  : "http://www.w3.org/2001/XMLSchema#integer"
                }
                """;
        var parsed = tester.parse(json);
        assertThat(parsed.getObject()).isEqualTo(dataFactory.getOWLLiteral(33));
    }

    @Test
    public void shouldRead_OWLLiteral_with_Datatype_Against_OWLAnnotationValue() throws IOException {
        var json = """
                {
                    "value" : 33,
                    "type"  : "http://www.w3.org/2001/XMLSchema#integer"
                }
                """;
        var parsed = annotationValueTester.parse(json);
        assertThat(parsed.getObject()).isEqualTo(dataFactory.getOWLLiteral(33));
    }

    @Test
    public void shouldWrite_OWLLiteral_with_Empty_LangTag() throws IOException {
        var literal = dataFactory.getOWLLiteral("Hello", "");
        var json = tester.write(literal);
        assertThat(json).hasJsonPathValue("value", "Hello");
        assertThat(json).hasJsonPathValue("lang", "");
    }

    @Test
    public void shouldRead_OWLLiteral_with_Empty_LangTag() throws IOException {
        var json = """
                {
                    "value" : "Hello"
                }
                """;
        var read = tester.parse(json);
        var literal = dataFactory.getOWLLiteral("Hello", "");
        assertThat(read.getObject()).isEqualTo(literal);

    }

    @Test
    public void shouldRoundTrip_OWLLiteral_with_LangTag_Against_OWLAnnotationValue() throws IOException {
        var json = """
                {
                    "value" : "Hello"
                }
                """;
        var read = annotationValueTester.parse(json);
        var literal = dataFactory.getOWLLiteral("Hello", "");
        assertThat(read.getObject()).isEqualTo(literal);
    }
}
