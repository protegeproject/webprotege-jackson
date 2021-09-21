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
public class OWLDataOneOf_Serialization_TestCase {
    
    @Autowired
    private JacksonTester<OWLDataRange> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDataOneOf dataRange;

    @BeforeEach
    void setUp() {
        dataRange = dataFactory.getOWLDataOneOf(
                dataFactory.getOWLLiteral("A", "en"),
                dataFactory.getOWLLiteral("B", "en")
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(dataRange);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("DataOneOf");

        assertThat(json).extractingJsonPathStringValue("$.literals[0].value").isEqualTo("A");
        assertThat(json).extractingJsonPathStringValue("$.literals[0].lang").isEqualTo("en");
        assertThat(json).extractingJsonPathStringValue("$.literals[1].value").isEqualTo("B");
        assertThat(json).extractingJsonPathStringValue("$.literals[1].lang").isEqualTo("en");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "DataOneOf",
                    "literals" : [{
                        "value"   : "A",
                        "lang"    : "en"
                    },
                    {
                        "value" : "B",
                        "lang"   : "en"
                    }]
                }
""";
        var dataRangeContent = tester.parse(json);
        var parsedDataRange = dataRangeContent.getObject();
        assertThat(parsedDataRange).isEqualTo(dataRange);
    }
}
