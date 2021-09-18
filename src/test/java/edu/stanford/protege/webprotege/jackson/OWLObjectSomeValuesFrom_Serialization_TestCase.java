package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-17
 */
@JsonTest
public class OWLObjectSomeValuesFrom_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLClassExpression> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLObjectSomeValuesFrom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLObjectSomeValuesFrom(
                dataFactory.getOWLObjectProperty(IRI.create("http://example.org/p")),
                dataFactory.getOWLClass(IRI.create("http://example.org/A"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("ObjectSomeValuesFrom");

        assertThat(json).extractingJsonPathStringValue("$.property.['@type']").isEqualTo("ObjectProperty");
        assertThat(json).extractingJsonPathStringValue("$.property.iri").isEqualTo("http://example.org/p");

        assertThat(json).extractingJsonPathStringValue("$.filler.['@type']").isEqualTo("Class");
        assertThat(json).extractingJsonPathStringValue("$.filler.iri").isEqualTo("http://example.org/A");


    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "ObjectSomeValuesFrom",
                    "property" : {
                        "@type" : "ObjectProperty",
                        "iri": "http://example.org/p"
                    },
                    "filler" : {
                        "@type" : "Class",
                        "iri" : "http://example.org/A"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
