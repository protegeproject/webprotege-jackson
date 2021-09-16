package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;
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
public class OWLDataProperty_Serialization_TestCase {


    @Autowired
    private JacksonTester<OWLDataProperty> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    @Test
    void shouldSerializeDataProperty() throws IOException {
        var iri = IRI.create("http://example.org/x");
        var prop = dataFactory.getOWLDataProperty(iri);
        var written = tester.write(prop);
        var json = written.getJson();
        System.out.println(json);

        assertThat(written).extractingJsonPathValue("['@type']").isEqualTo("DataProperty");
        assertThat(written).extractingJsonPathValue("iri").isEqualTo(iri.toString());


        var parsed = tester.parse(json);
        var parsedProp = parsed.getObject();
        assertThat(parsedProp).isEqualTo(prop);
    }

    @Test
    void shouldDeserializeDataProperty() throws IOException {
        var iri = IRI.create("http://example.org/x");
        var prop = dataFactory.getOWLDataProperty(iri);
        var json = """
                {"@type" : "DataProperty", "iri" : "http://example.org/x"}
                """;
        var parsed = tester.parse(json);
        var parsedProp = parsed.getObject();
        assertThat(parsedProp).isEqualTo(prop);
    }

    @Test
    void shouldDeserializeDataPropertyWithLegacyTypeNames() throws IOException {
        var iri = IRI.create("http://example.org/x");
        var prop = dataFactory.getOWLDataProperty(iri);
        var json = """
                {"type" : "owl:DatatypeProperty", "iri" : "http://example.org/x"}
                """;
        var parsed = tester.parse(json);
        var parsedProp = parsed.getObject();
        assertThat(parsedProp).isEqualTo(prop);
    }
}
