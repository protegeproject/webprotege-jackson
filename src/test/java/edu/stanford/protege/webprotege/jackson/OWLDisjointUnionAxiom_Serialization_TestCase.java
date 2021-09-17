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
public class OWLDisjointUnionAxiom_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDisjointUnionAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLDisjointUnionAxiom(
                dataFactory.getOWLClass(IRI.create("http://example.org/A")),
                Set.of(
                        dataFactory.getOWLClass(IRI.create("http://example.org/B")),
                        dataFactory.getOWLClass(IRI.create("http://example.org/C"))
                )
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("DisjointUnion");
        assertThat(json).extractingJsonPathStringValue("$.class.['@type']").isEqualTo("Class");
        assertThat(json).extractingJsonPathStringValue("$.class.iri").isEqualTo("http://example.org/A");
        assertThat(json).extractingJsonPathStringValue("$.classExpressions[0].['@type']").isEqualTo("Class");
        assertThat(json).extractingJsonPathStringValue("$.classExpressions[0].iri").isEqualTo("http://example.org/B");
        assertThat(json).extractingJsonPathStringValue("$.classExpressions[1].['@type']").isEqualTo("Class");
        assertThat(json).extractingJsonPathStringValue("$.classExpressions[1].iri").isEqualTo("http://example.org/C");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "DisjointUnion",
                    "class" : {
                        "@type" : "Class",
                        "iri": "http://example.org/A"
                    },
                    "classExpressions" : [{
                        "@type" : "Class",
                        "iri" : "http://example.org/B"
                    },
                    {
                        "@type" : "Class",
                        "iri" : "http://example.org/C"
                    }
                    ]
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
