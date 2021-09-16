package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
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
public class OWLDataPropertyAxiom_Serialization_TestCase {



    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDataPropertyAssertionAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLDataPropertyAssertionAxiom(
                dataFactory.getOWLDataProperty(IRI.create("http://example.org/p")),
                dataFactory.getOWLNamedIndividual(IRI.create("http://example.org/a")),
                dataFactory.getOWLLiteral("Hello")
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("['@type']").isEqualTo("DataPropertyAssertion");
        assertThat(json).hasJsonPathValue("subject");
        assertThat(json).hasJsonPathValue("property");
        assertThat(json).hasJsonPathValue("object");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "DataPropertyAssertion",
                    "subject" : {
                          "@type" : "NamedIndividual",
                          "iri": "http://example.org/a"
                        },
                    "property" : {
                          "@type" : "DataProperty",
                          "iri" : "http://example.org/p"
                        },
                    "object" : {
                          "value" : "Hello"
                        }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
