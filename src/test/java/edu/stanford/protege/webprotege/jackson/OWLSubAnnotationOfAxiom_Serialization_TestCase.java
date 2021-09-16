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
 * 2021-09-16
 */
@JsonTest
public class OWLSubAnnotationOfAxiom_Serialization_TestCase {
    
    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLSubAnnotationPropertyOfAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLSubAnnotationPropertyOfAxiom(
                dataFactory.getOWLAnnotationProperty(IRI.create("http://example.org/p")),
                dataFactory.getOWLAnnotationProperty(IRI.create("http://example.org/q"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("SubAnnotationPropertyOf");
        assertThat(json).extractingJsonPathStringValue("$.subProperty.['@type']").isEqualTo("AnnotationProperty");
        assertThat(json).extractingJsonPathStringValue("$.subProperty.iri").isEqualTo("http://example.org/p");
        assertThat(json).extractingJsonPathStringValue("$.superProperty.['@type']").isEqualTo("AnnotationProperty");
        assertThat(json).extractingJsonPathStringValue("$.superProperty.iri").isEqualTo("http://example.org/q");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "SubAnnotationPropertyOf",
                    "subProperty" : {
                        "@type" : "AnnotationProperty",
                        "iri": "http://example.org/p"
                    },
                    "superProperty" : {
                        "@type" : "AnnotationProperty",
                        "iri" : "http://example.org/q"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
