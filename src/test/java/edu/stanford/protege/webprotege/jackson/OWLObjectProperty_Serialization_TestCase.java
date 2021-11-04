package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-13
 */
@JsonTest
public class OWLObjectProperty_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLProperty> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    @Test
    void shouldSerializeObjectProperty() throws IOException {
        var iri = IRI.create("http://example.org/x");
        var prop = dataFactory.getOWLObjectProperty(iri);
        var written = tester.write(prop);
        var json = written.getJson();
        System.out.println(json);

        assertThat(written).extractingJsonPathValue("['@type']").isEqualTo("ObjectProperty");
        assertThat(written).extractingJsonPathValue("iri").isEqualTo(iri.toString());


        var parsed = tester.parse(json);
        var parsedProp = parsed.getObject();
        assertThat(parsedProp).isEqualTo(prop);
    }

    @Test
    void shouldDeserializeObjectProperty() throws IOException {
        var iri = IRI.create("http://example.org/x");
        var prop = dataFactory.getOWLObjectProperty(iri);
        var json = """
                {"@type" : "ObjectProperty", "iri" : "http://example.org/x"}
                """;
        var parsed = tester.parse(json);
        var parsedProp = parsed.getObject();
        assertThat(parsedProp).isEqualTo(prop);
    }

    @Test
    void shouldDeserializeObjectPropertyWithLegacyTypeNames() throws IOException {
        var iri = IRI.create("http://example.org/x");
        var prop = dataFactory.getOWLObjectProperty(iri);
        var json = """
                {"type" : "owl:ObjectProperty", "iri" : "http://example.org/x"}
                """;
        var parsed = tester.parse(json);
        var parsedProp = parsed.getObject();
        assertThat(parsedProp).isEqualTo(prop);
    }
}
