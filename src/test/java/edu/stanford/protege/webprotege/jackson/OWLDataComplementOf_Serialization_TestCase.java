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
public class OWLDataComplementOf_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLDataRange> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDataComplementOf dr;

    @BeforeEach
    void setUp() {
        dr = dataFactory.getOWLDataComplementOf(
                dataFactory.getOWLDatatype(IRI.create("http://example.org/D"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(dr);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("DataComplementOf");

        assertThat(json).extractingJsonPathStringValue("$.dataRange.['@type']").isEqualTo("Datatype");
        assertThat(json).extractingJsonPathStringValue("$.dataRange.iri").isEqualTo("http://example.org/D");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "DataComplementOf",
                    "dataRange" : {
                        "@type" : "Datatype",
                        "iri"   : "http://example.org/D"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedDataRange = axiomContent.getObject();
        assertThat(parsedDataRange).isEqualTo(dr);
    }
}
