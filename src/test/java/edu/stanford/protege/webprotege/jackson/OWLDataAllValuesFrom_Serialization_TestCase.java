package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;
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
public class OWLDataAllValuesFrom_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLClassExpression> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDataAllValuesFrom axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLDataAllValuesFrom(
                dataFactory.getOWLDataProperty(IRI.create("http://example.org/p")),
                dataFactory.getOWLDatatype(IRI.create("http://example.org/D"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("DataAllValuesFrom");

        assertThat(json).extractingJsonPathStringValue("$.property.['@type']").isEqualTo("DataProperty");
        assertThat(json).extractingJsonPathStringValue("$.property.iri").isEqualTo("http://example.org/p");

        assertThat(json).extractingJsonPathStringValue("$.filler.['@type']").isEqualTo("Datatype");
        assertThat(json).extractingJsonPathStringValue("$.filler.iri").isEqualTo("http://example.org/D");


    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "DataAllValuesFrom",
                    "property" : {
                        "@type" : "DataProperty",
                        "iri": "http://example.org/p"
                    },
                    "filler" : {
                        "@type" : "Datatype",
                        "value" : "http://example.org/D"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
