package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
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
public class OWLObjectUnionOf_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLClassExpression> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLObjectUnionOf axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLObjectUnionOf(
                dataFactory.getOWLClass(IRI.create("http://example.org/A")),
                dataFactory.getOWLClass(IRI.create("http://example.org/B"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("ObjectUnionOf");

        assertThat(json).extractingJsonPathStringValue("$.classExpressions[0].['@type']").isEqualTo("Class");
        assertThat(json).extractingJsonPathStringValue("$.classExpressions[0].iri").isEqualTo("http://example.org/A");
        assertThat(json).extractingJsonPathStringValue("$.classExpressions[1].['@type']").isEqualTo("Class");
        assertThat(json).extractingJsonPathStringValue("$.classExpressions[1].iri").isEqualTo("http://example.org/B");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "ObjectUnionOf",
                    "classExpressions" : [{
                        "@type" : "Class",
                        "iri"   : "http://example.org/A"
                    },
                    {
                        "@type" : "Class",
                        "iri"   : "http://example.org/B"
                    }]
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
