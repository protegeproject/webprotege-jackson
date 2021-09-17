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
public class OWLHasKeyAxiom_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLHasKeyAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLHasKeyAxiom(
                dataFactory.getOWLClass(IRI.create("http://example.org/A")),
                Set.of(
                        dataFactory.getOWLObjectProperty(IRI.create("http://example.org/p")),
                        dataFactory.getOWLDataProperty(IRI.create("http://example.org/q"))
                )
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("HasKey");
        assertThat(json).extractingJsonPathStringValue("$.classExpression.['@type']").isEqualTo("Class");
        assertThat(json).extractingJsonPathStringValue("$.classExpression.iri").isEqualTo("http://example.org/A");

        assertThat(json).extractingJsonPathStringValue("$.propertyExpressions[0].['@type']").isEqualTo("ObjectProperty");
        assertThat(json).extractingJsonPathStringValue("$.propertyExpressions[0].iri").isEqualTo("http://example.org/p");
        assertThat(json).extractingJsonPathStringValue("$.propertyExpressions[1].['@type']").isEqualTo("DataProperty");
        assertThat(json).extractingJsonPathStringValue("$.propertyExpressions[1].iri").isEqualTo("http://example.org/q");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "HasKey",
                    "classExpression" : {
                        "@type" : "Class",
                        "iri": "http://example.org/A"
                    },
                    "propertyExpressions" : [{
                        "@type" : "ObjectProperty",
                        "iri" : "http://example.org/p"
                    },{
                        "@type" : "DataProperty",
                        "iri" : "http://example.org/q"
                    }
                    ]
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
