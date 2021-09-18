package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataFactory;
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
public class OWLDataMinCardinality_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLClassExpression> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDataMinCardinality axiom;

    @BeforeEach
    void setUp() {
        axiom = dataFactory.getOWLDataMinCardinality(
                3,
                dataFactory.getOWLDataProperty(IRI.create("http://example.org/p")),
                dataFactory.getOWLDatatype(IRI.create("http://example.org/D"))
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(axiom);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("DataMinCardinality");

        assertThat(json).extractingJsonPathNumberValue("$.cardinality").isEqualTo(3);

        assertThat(json).extractingJsonPathStringValue("$.property.['@type']").isEqualTo("DataProperty");
        assertThat(json).extractingJsonPathStringValue("$.property.iri").isEqualTo("http://example.org/p");

        assertThat(json).extractingJsonPathStringValue("$.filler.['@type']").isEqualTo("Datatype");
        assertThat(json).extractingJsonPathStringValue("$.filler.iri").isEqualTo("http://example.org/D");


    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "DataMinCardinality",
                    "cardinality" : 3,
                    "property" : {
                        "@type" : "DataProperty",
                        "iri": "http://example.org/p"
                    },
                    "filler" : {
                        "@type" : "Datatype",
                        "iri" : "http://example.org/D"
                    }
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(axiom);
    }
}
