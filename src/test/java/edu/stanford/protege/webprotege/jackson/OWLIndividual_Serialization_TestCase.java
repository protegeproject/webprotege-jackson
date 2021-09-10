package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-04
 */
@JsonTest
public class OWLIndividual_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLIndividual> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    @Test
    void shouldSerializeAnonymousIndividual() throws IOException {
        var individual = dataFactory.getOWLAnonymousIndividual();
        var json = tester.write(individual);
        System.out.println(json.getJson());
        var parsed = tester.parse(json.getJson());
        var parsedIndividual = parsed.getObject();
        assertThat(individual).isEqualTo(parsedIndividual);
    }

    @Test
    void shouldSerializeNamedIndividual() throws IOException {
        var individual = dataFactory.getOWLNamedIndividual(IRI.create("http://example.org/i"));
        var json = tester.write(individual);
        System.out.println(json.getJson());
        var parsed = tester.parse(json.getJson());
        var parsedIndividual = parsed.getObject();
        assertThat(individual).isEqualTo(parsedIndividual);
    }
}
