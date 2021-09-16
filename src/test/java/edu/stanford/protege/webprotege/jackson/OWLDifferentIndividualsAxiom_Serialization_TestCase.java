package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
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
public class OWLDifferentIndividualsAxiom_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDifferentIndividualsAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLDifferentIndividualsAxiom(
                dataFactory.getOWLNamedIndividual(IRI.create("http://example.org/i")),
                dataFactory.getOWLNamedIndividual(IRI.create("http://example.org/j"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        var parsed = tester.parse(json.getJson());
        assertThat(parsed.getObject()).isEqualTo(axiom);
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "DifferentIndividuals",
                    "individuals" : [
                         {
                          "@type" : "NamedIndividual",
                          "iri": "http://example.org/i"
                        },
                        {
                          "@type" : "NamedIndividual",
                          "iri" : "http://example.org/j"
                        }
                    ],
                    "annotations" : []
                }
""".stripIndent();
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
