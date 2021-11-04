package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-11-04
 */
//@JsonTest
public class OWLAnnotationValue_Serialization_TestCase {


//    @Autowired
//    private OWLDataFactory dataFactory;
//
//    @Autowired
//    private JacksonTester<OWLAnnotationValue> tester;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//    }
//
//    @Test
//    public void shouldRead_OWLLiteral() throws IOException {
//        var json = """
//                {
//                    "value" : 33,
//                    "datatype"  : "http://www.w3.org/2001/XMLSchema#integer"
//                }
//                """;
//        var parsed = tester.parse(json);
//        assertThat(parsed.getObject()).isEqualTo(dataFactory.getOWLLiteral(33));
//    }
//    @Test
//    public void shouldRead_IRI() throws IOException {
//        var json = """
//                {
//                    "iri"  : "http://example.org/A"
//                }
//                """;
//        var parsed = tester.parse(json);
//        assertThat(parsed.getObject()).isEqualTo(IRI.create("http://example.org/A"));
//    }
}
