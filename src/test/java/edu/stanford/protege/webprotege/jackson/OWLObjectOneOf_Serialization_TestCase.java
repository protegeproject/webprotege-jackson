package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
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
public class OWLObjectOneOf_Serialization_TestCase {
    
    @Autowired
    private JacksonTester<OWLClassExpression> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLObjectOneOf axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLObjectOneOf(
                dataFactory.getOWLNamedIndividual(IRI.create("http://example.org/i")),
                dataFactory.getOWLNamedIndividual(IRI.create("http://example.org/j"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("ObjectOneOf");

        assertThat(json).extractingJsonPathStringValue("$.individuals[0].['@type']").isEqualTo("NamedIndividual");
        assertThat(json).extractingJsonPathStringValue("$.individuals[0].iri").isEqualTo("http://example.org/i");
        assertThat(json).extractingJsonPathStringValue("$.individuals[1].['@type']").isEqualTo("NamedIndividual");
        assertThat(json).extractingJsonPathStringValue("$.individuals[1].iri").isEqualTo("http://example.org/j");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "ObjectOneOf",
                    "individuals" : [{
                        "@type" : "NamedIndividual",
                        "iri"   : "http://example.org/i"
                    },
                    {
                        "@type" : "NamedIndividual",
                        "iri"   : "http://example.org/j"
                    }]
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
