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
public class OWLDisjointClassesAxiom_Serialization_TestCase {


    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDisjointClassesAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLDisjointClassesAxiom(
                dataFactory.getOWLClass(IRI.create("http://example.org/A")),
                dataFactory.getOWLClass(IRI.create("http://example.org/B"))
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
                    "@type" : "DisjointClasses",
                    "classExpressions" : [
                         {
                          "@type" : "Class",
                          "iri": "http://example.org/A"
                        },
                        {
                          "@type" : "Class",
                          "iri" : "http://example.org/B"
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
