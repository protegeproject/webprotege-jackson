package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
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
public class OWLObjectHasValue_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLClassExpression> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLObjectHasValue axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLObjectHasValue(
                dataFactory.getOWLObjectProperty(IRI.create("http://example.org/p")),
                dataFactory.getOWLNamedIndividual(IRI.create("http://example.org/i"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("ObjectHasValue");

        assertThat(json).extractingJsonPathStringValue("$.property.['@type']").isEqualTo("ObjectProperty");
        assertThat(json).extractingJsonPathStringValue("$.property.iri").isEqualTo("http://example.org/p");

        assertThat(json).extractingJsonPathStringValue("$.filler.['@type']").isEqualTo("NamedIndividual");
        assertThat(json).extractingJsonPathStringValue("$.filler.iri").isEqualTo("http://example.org/i");


    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "ObjectHasValue",
                    "property" : {
                        "@type" : "ObjectProperty",
                        "iri": "http://example.org/p"
                    },
                    "filler" : {
                        "@type" : "NamedIndividual",
                        "iri" : "http://example.org/i"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
