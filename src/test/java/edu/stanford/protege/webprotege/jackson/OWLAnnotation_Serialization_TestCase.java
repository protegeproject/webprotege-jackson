package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-12
 */
@JsonTest
public class OWLAnnotation_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLAnnotation> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    @Test
    void shouldSerializeAnnotation() throws IOException {
        var value = IRI.create("http://example.org/x");
        var property = dataFactory.getOWLAnnotationProperty(IRI.create("http://example.org/p"));
        var annotation = dataFactory.getOWLAnnotation(property, value);
        var written = tester.write(annotation);
        var json = written.getJson();
        System.out.println(json);
        assertThat(written).extractingJsonPathValue("property").isEqualTo("http://example.org/p");
        assertThat(written).extractingJsonPathValue("value.iri").isEqualTo(value.toString());
    }

    @Test
    void shouldDeserializeAnnotation() throws IOException {
        var value = IRI.create("http://example.org/x");
        var property = dataFactory.getOWLAnnotationProperty(IRI.create("http://example.org/p"));
        var annotation = dataFactory.getOWLAnnotation(property, value);
        var json = """
                {"property" : {"@type":"AnnotationProperty","iri":"http://example.org/p"}, "value" : {"iri":"http://example.org/x"}}
                """;
        var parsed = tester.parse(json);
        var parsedObject = parsed.getObject();
        assertThat(parsedObject).isEqualTo(annotation);
    }
}
