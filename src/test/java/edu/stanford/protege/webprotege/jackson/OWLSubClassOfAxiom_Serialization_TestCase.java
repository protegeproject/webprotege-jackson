package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
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
public class OWLSubClassOfAxiom_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLSubClassOfAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLSubClassOfAxiom(
                dataFactory.getOWLClass(IRI.create("http://example.org/A")),
                dataFactory.getOWLClass(IRI.create("http://example.org/B"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("SubClassOf");
        assertThat(json).extractingJsonPathStringValue("$.subClass.['@type']").isEqualTo("Class");
        assertThat(json).extractingJsonPathStringValue("$.subClass.iri").isEqualTo("http://example.org/A");
        assertThat(json).extractingJsonPathStringValue("$.superClass.['@type']").isEqualTo("Class");
        assertThat(json).extractingJsonPathStringValue("$.superClass.iri").isEqualTo("http://example.org/B");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "SubClassOf",
                    "subClass" : {
                        "@type" : "Class",
                        "iri": "http://example.org/A"
                    },
                    "superClass" : {
                        "@type" : "Class",
                        "iri" : "http://example.org/B"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
