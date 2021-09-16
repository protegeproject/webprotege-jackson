package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-16
 */
@JsonTest
public class OWLSubObjectPropertyOfAxiom_Serialization_TestCase {


    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLSubObjectPropertyOfAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLSubObjectPropertyOfAxiom(
                dataFactory.getOWLObjectProperty(IRI.create("http://example.org/p")),
                dataFactory.getOWLObjectProperty(IRI.create("http://example.org/q"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("SubObjectPropertyOf");
        assertThat(json).extractingJsonPathStringValue("$.subProperty.['@type']").isEqualTo("ObjectProperty");
        assertThat(json).extractingJsonPathStringValue("$.subProperty.iri").isEqualTo("http://example.org/p");
        assertThat(json).extractingJsonPathStringValue("$.superProperty.['@type']").isEqualTo("ObjectProperty");
        assertThat(json).extractingJsonPathStringValue("$.superProperty.iri").isEqualTo("http://example.org/q");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "SubObjectPropertyOf",
                    "subProperty" : {
                        "@type" : "ObjectProperty",
                        "iri": "http://example.org/p"
                    },
                    "superProperty" : {
                        "@type" : "ObjectProperty",
                        "iri" : "http://example.org/q"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
