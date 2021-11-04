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
 * 2021-09-02
 */
@JsonTest
public class OWLAnnotationAssertionAxiom_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLAnnotationAssertionAxiom axiomWithLiteralValue;

    private OWLAnnotationAssertionAxiom axiomWithIriValue;

    private OWLAnnotationAssertionAxiom axiomWithAnonymousIndividualValue;

    @BeforeEach
    void setUp() {
        axiomWithLiteralValue = dataFactory.getOWLAnnotationAssertionAxiom(
                dataFactory.getOWLAnnotationProperty(IRI.create("http://example.org/p")),
                IRI.create("http://example.org/A"),
                dataFactory.getOWLLiteral("Hello", "en")
        );
        axiomWithIriValue = dataFactory.getOWLAnnotationAssertionAxiom(
                dataFactory.getOWLAnnotationProperty(IRI.create("http://example.org/p")),
                IRI.create("http://example.org/A"),
                IRI.create("http://example.org/B")
        );
        axiomWithAnonymousIndividualValue = dataFactory.getOWLAnnotationAssertionAxiom(
                dataFactory.getOWLAnnotationProperty(IRI.create("http://example.org/p")),
                IRI.create("http://example.org/A"),
                dataFactory.getOWLAnonymousIndividual("B2")
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiomWithLiteralValue);
        System.out.println(json.getJson());
    }

    @Test
    void shouldDeserializeAxiomWithIri() throws IOException {
        var json = """
                {
                    "@type" : "AnnotationAssertion",
                    "subject" : {
                        "iri" : "http://example.org/A"
                    },
                    "property" : {
                        "@type" : "AnnotationProperty",
                        "iri"   : "http://example.org/p"
                    },
                    "value" : {
                        "iri" : "http://example.org/B"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiomWithIriValue);
    }

    @Test
    void shouldDeserializeAxiomWithNodeId() throws IOException {
        var json = """
                {
                    "@type" : "AnnotationAssertion",
                    "subject" : {
                        "iri" : "http://example.org/A"
                    },
                    "property" : {
                        "@type" : "AnnotationProperty",
                        "iri"   : "http://example.org/p"
                    },
                    "value" : {
                        "nodeId" : "B2"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiomWithAnonymousIndividualValue);
    }

    @Test
    void shouldDeserializeAxiomWithLiteral() throws IOException {
        var json = """
                {
                    "@type" : "AnnotationAssertion",
                    "subject" : {
                        "iri" : "http://example.org/A"
                    },
                    "property" : {
                        "@type" : "AnnotationProperty",
                        "iri"   : "http://example.org/p"
                    },
                    "value" : {
                        "value" : "Hello",
                        "lang" : "en"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiomWithLiteralValue);
    }
}
