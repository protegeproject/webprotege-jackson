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
 * 2021-09-21
 */
@JsonTest
public class OWLDataIntersectionOf_Serialization_TestCase {


    @Autowired
    private JacksonTester<OWLDataRange> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDataIntersectionOf dr;

    @BeforeEach
    void setUp() {
        dr = dataFactory.getOWLDataIntersectionOf(
                dataFactory.getOWLDatatype(IRI.create("http://example.org/D1")),
                dataFactory.getOWLDatatype(IRI.create("http://example.org/D2"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(dr);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("DataIntersectionOf");

        assertThat(json).extractingJsonPathStringValue("$.dataRanges[0].['@type']").isEqualTo("Datatype");
        assertThat(json).extractingJsonPathStringValue("$.dataRanges[0].iri").isEqualTo("http://example.org/D1");
        assertThat(json).extractingJsonPathStringValue("$.dataRanges[1].['@type']").isEqualTo("Datatype");
        assertThat(json).extractingJsonPathStringValue("$.dataRanges[1].iri").isEqualTo("http://example.org/D2");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "DataIntersectionOf",
                    "dataRanges" : [{
                        "@type" : "Datatype",
                        "iri"   : "http://example.org/D1"
                    },
                    {
                        "@type" : "Datatype",
                        "iri"   : "http://example.org/D2"
                    }]
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(dr);
    }
}
