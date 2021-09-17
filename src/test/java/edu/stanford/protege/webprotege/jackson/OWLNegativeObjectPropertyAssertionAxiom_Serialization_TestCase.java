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
 * 2021-09-17
 */
@JsonTest
public class OWLNegativeObjectPropertyAssertionAxiom_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLNegativeObjectPropertyAssertionAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLNegativeObjectPropertyAssertionAxiom(
                dataFactory.getOWLObjectProperty(IRI.create("http://example.org/p")),
                dataFactory.getOWLNamedIndividual(IRI.create("http://example.org/a")),
                dataFactory.getOWLNamedIndividual(IRI.create("http://example.org/b"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("['@type']").isEqualTo("NegativeObjectPropertyAssertion");
        assertThat(json).hasJsonPathValue("subject");
        assertThat(json).hasJsonPathValue("property");
        assertThat(json).hasJsonPathValue("object");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "NegativeObjectPropertyAssertion",
                    "subject" : {
                          "@type" : "NamedIndividual",
                          "iri": "http://example.org/a"
                        },
                    "property" : {
                          "@type" : "ObjectProperty",
                          "iri" : "http://example.org/p"
                        },
                    "object" : {
                          "@type" : "NamedIndividual",
                          "iri" : "http://example.org/b"
                        }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
