package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.annotation.JsonTypeName;
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
 * 2021-09-04
 */
@JsonTest
public class OWLClassAssertionAxiom_Serialization_TestCase {


    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLClassAssertionAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLClassAssertionAxiom(
                dataFactory.getOWLClass(IRI.create("http://example.org/A")),
                dataFactory.getOWLNamedIndividual(IRI.create("http://example.org/a"))
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
                    "@type" : "ClassAssertion",
                    "classExpression" : {
                        "@type" : "Class",
                        "iri": "http://example.org/A"
                    },
                    "individual" : {
                        "@type" : "NamedIndividual",
                        "iri" : "http://example.org/a"
                    },
                    "annotations" : []
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
