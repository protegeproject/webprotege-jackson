package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
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
public class OWLDataPropertyDomainAxiom_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDataPropertyDomainAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLDataPropertyDomainAxiom(
                dataFactory.getOWLDataProperty(IRI.create("http://example.org/p")),
                dataFactory.getOWLClass(IRI.create("http://example.org/A"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("DataPropertyDomain");
        assertThat(json).extractingJsonPathStringValue("$.property.['@type']").isEqualTo("DataProperty");
        assertThat(json).extractingJsonPathStringValue("$.property.iri").isEqualTo("http://example.org/p");
        assertThat(json).extractingJsonPathStringValue("$.domain.['@type']").isEqualTo("Class");
        assertThat(json).extractingJsonPathStringValue("$.domain.iri").isEqualTo("http://example.org/A");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "DataPropertyDomain",
                    "property" : {
                        "@type" : "DataProperty",
                        "iri": "http://example.org/p"
                    },
                    "domain" : {
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
