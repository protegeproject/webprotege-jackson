package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyDomainAxiom;
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
public class OWLAnnotationPropertyDomainAxiom_Serialization_TestCase {
    
    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLAnnotationPropertyDomainAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLAnnotationPropertyDomainAxiom(
                dataFactory.getOWLAnnotationProperty(IRI.create("http://example.org/p")),
                IRI.create("http://example.org/A")
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("AnnotationPropertyDomain");
        assertThat(json).extractingJsonPathStringValue("$.property.['@type']").isEqualTo("AnnotationProperty");
        assertThat(json).extractingJsonPathStringValue("$.property.iri").isEqualTo("http://example.org/p");
        assertThat(json).extractingJsonPathStringValue("$.domain").isEqualTo("http://example.org/A");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "AnnotationPropertyDomain",
                    "property" : {
                        "@type" : "AnnotationProperty",
                        "iri": "http://example.org/p"
                    },
                    "domain" : "http://example.org/A"
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
