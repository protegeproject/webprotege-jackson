package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-13
 */
@JsonTest
public class OWLEntity_Serialization_TestCase {

    public static final String IRI_STRING = "http://example.org/x";

    @Autowired
    private JacksonTester<OWLEntity> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    private final IRI iri = IRI.create(IRI_STRING);

    @Test
    void shouldWriteOwlClass() throws IOException {
        var entity = dataFactory.getOWLClass(iri);
        var json = tester.write(entity);
        assertThat(json).hasJsonPathValue("iri", IRI_STRING);
        assertThat(json).hasJsonPathValue("type", "owl:Class");
    }
    
    @Test
    void shouldWriteOwlDatatype() throws IOException {
        var entity = dataFactory.getOWLDatatype(iri);
        var json = tester.write(entity);
        assertThat(json).extractingJsonPathStringValue("iri").isEqualTo(IRI_STRING);
        assertThat(json).extractingJsonPathStringValue("type").isEqualTo("rdfs:Datatype");
    }
    
    @Test
    void shouldWriteOwlNamedIndividual() throws IOException {
        var entity = dataFactory.getOWLNamedIndividual(iri);
        var json = tester.write(entity);
        assertThat(json).extractingJsonPathStringValue("iri").isEqualTo(IRI_STRING);
        assertThat(json).extractingJsonPathStringValue("type").isEqualTo("owl:NamedIndividual");
    }

    @Test
    void shouldWriteOwlObjectProperty() throws IOException {
        var entity = dataFactory.getOWLObjectProperty(iri);
        var json = tester.write(entity);
        assertThat(json).extractingJsonPathStringValue("iri").isEqualTo(IRI_STRING);
        assertThat(json).extractingJsonPathStringValue("type").isEqualTo("owl:ObjectProperty");
    }

    @Test
    void shouldWriteOwlDataProperty() throws IOException {
        var entity = dataFactory.getOWLDataProperty(iri);
        var json = tester.write(entity);
        assertThat(json).extractingJsonPathStringValue("iri").isEqualTo(IRI_STRING);
        assertThat(json).extractingJsonPathStringValue("type").isEqualTo("owl:DatatypeProperty");
    }

    @Test
    void shouldWriteOwlAnnotationProperty() throws IOException {
        var entity = dataFactory.getOWLAnnotationProperty(iri);
        var json = tester.write(entity);
        assertThat(json).extractingJsonPathStringValue("iri").isEqualTo(IRI_STRING);
        assertThat(json).extractingJsonPathStringValue("type").isEqualTo("owl:AnnotationProperty");
    }

    @Test
    void shouldReadOwlClass() throws IOException {
        var json = """
                {
                    "iri" : "http://example.org/x",
                    "type" : "owl:Class"
                }
                """;
        var parsed = tester.parse(json);
        assertThat(parsed.getObject()).isEqualTo(dataFactory.getOWLClass(IRI.create(IRI_STRING)));
    }

    @Test
    void shouldReadOwlDatatype() throws IOException {
        var json = """
                {
                    "iri" : "http://example.org/x",
                    "type" : "rdfs:Datatype"
                }
                """;
        var parsed = tester.parse(json);
        assertThat(parsed.getObject()).isEqualTo(dataFactory.getOWLDatatype(IRI.create(IRI_STRING)));
    }

    @Test
    void shouldReadOwlNamedIndividual() throws IOException {
        var json = """
                {
                    "iri" : "http://example.org/x",
                    "type" : "owl:NamedIndividual"
                }
                """;
        var parsed = tester.parse(json);
        assertThat(parsed.getObject()).isEqualTo(dataFactory.getOWLNamedIndividual(IRI.create(IRI_STRING)));
    }

    @Test
    void shouldReadOwlObjectProperty() throws IOException {
        var json = """
                {
                    "iri" : "http://example.org/x",
                    "type" : "owl:ObjectProperty"
                }
                """;
        var parsed = tester.parse(json);
        assertThat(parsed.getObject()).isEqualTo(dataFactory.getOWLObjectProperty(IRI.create(IRI_STRING)));
    }


    @Test
    void shouldReadOwlDataProperty() throws IOException {
        var json = """
                {
                    "iri" : "http://example.org/x",
                    "type" : "owl:DatatypeProperty"
                }
                """;
        var parsed = tester.parse(json);
        assertThat(parsed.getObject()).isEqualTo(dataFactory.getOWLDataProperty(IRI.create(IRI_STRING)));
    }

    @Test
    void shouldReadOwlAnnotationProperty() throws IOException {
        var json = """
                {
                    "iri" : "http://example.org/x",
                    "type" : "owl:AnnotationProperty"
                }
                """;
        var parsed = tester.parse(json);
        assertThat(parsed.getObject()).isEqualTo(dataFactory.getOWLAnnotationProperty(IRI.create(IRI_STRING)));
    }
}
