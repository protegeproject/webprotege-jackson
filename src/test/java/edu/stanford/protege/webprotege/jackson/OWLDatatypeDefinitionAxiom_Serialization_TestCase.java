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
public class OWLDatatypeDefinitionAxiom_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLAxiom> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDatatypeDefinitionAxiom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLDatatypeDefinitionAxiom(
                dataFactory.getOWLDatatype(IRI.create("http://example.org/D")),
                dataFactory.getOWLDatatype(IRI.create("http://example.org/E"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("DatatypeDefinition");
        assertThat(json).extractingJsonPathStringValue("$.datatype.['@type']").isEqualTo("Datatype");
        assertThat(json).extractingJsonPathStringValue("$.datatype.iri").isEqualTo("http://example.org/D");
        assertThat(json).extractingJsonPathStringValue("$.dataRange.['@type']").isEqualTo("Datatype");
        assertThat(json).extractingJsonPathStringValue("$.dataRange.iri").isEqualTo("http://example.org/E");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "DatatypeDefinition",
                    "datatype" : {
                        "@type" : "Datatype",
                        "iri": "http://example.org/D"
                    },
                    "dataRange" : {
                        "@type" : "Datatype",
                        "iri" : "http://example.org/E"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
