package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDatatypeRestriction;
import org.semanticweb.owlapi.vocab.OWLFacet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-21
 */
@JsonTest
public class OWLDatatypeRestriction_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLDataRange> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private OWLDatatypeRestriction dr;

    @BeforeEach
    void setUp() {
        dr = dataFactory.getOWLDatatypeRestriction(
                dataFactory.getOWLDatatype(IRI.create("http://example.org/D1")), 
                Collections.singleton(
                        dataFactory.getOWLFacetRestriction(OWLFacet.MIN_INCLUSIVE, 33)
                )
        );
    }

    @Test
    void shouldSerializeAxiom() throws IOException {
        var json = tester.write(dr);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("$.['@type']").isEqualTo("DatatypeRestriction");

        assertThat(json).extractingJsonPathStringValue("$.facetRestrictions[0].facet").isEqualTo("http://www.w3.org/2001/XMLSchema#minInclusive");
        assertThat(json).extractingJsonPathStringValue("$.facetRestrictions[0].value.datatype").isEqualTo("http://www.w3.org/2001/XMLSchema#integer");
        assertThat(json).extractingJsonPathStringValue("$.facetRestrictions[0].value.value").isEqualTo("33");
    }

    @Test
    void shouldDeserializeAxiom() throws IOException {
        var json = """
                {
                    "@type" : "DatatypeRestriction",
                    "datatype" : {
                        "@type" : "Datatype",
                        "iri"   : "http://example.org/D1"
                    },
                    "facetRestrictions" : [{
                        "facet" : "http://www.w3.org/2001/XMLSchema#minInclusive",
                        "value" : {"type" : "http://www.w3.org/2001/XMLSchema#integer", "value" : "33"}
                    }]
                }
""";
        var axiomContent = tester.parse(json);
        var parsedAxiom = axiomContent.getObject();
        assertThat(parsedAxiom).isEqualTo(dr);
    }
}
