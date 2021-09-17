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
public class OWLEquivalentDataPropertiesAxiom_Serialization_TestCase {
    
    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLEquivalentDataPropertiesAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLEquivalentDataPropertiesAxiom(
                dataFactory.getOWLDataProperty(IRI.create("http://example.org/p")),
                dataFactory.getOWLDataProperty(IRI.create("http://example.org/q"))
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
                    "@type" : "EquivalentDataProperties",
                    "properties" : [
                         {
                          "@type" : "DataProperty",
                          "iri": "http://example.org/p"
                        },
                        {
                          "@type" : "DataProperty",
                          "iri" : "http://example.org/q"
                        }
                    ]
                }
""".stripIndent();
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
