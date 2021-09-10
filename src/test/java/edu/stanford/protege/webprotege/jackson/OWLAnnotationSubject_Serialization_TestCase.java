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
 * 2021-09-04
 */
@JsonTest
public class OWLAnnotationSubject_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLAnnotationSubject> tester;

    @Autowired
    private OWLDataFactory dataFactory;


    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldSerializeIri() throws IOException {
        var subject = IRI.create("http://example.org/x");
        var json = tester.write(subject);
        System.out.println(json.getJson());
        var parsed = tester.parse(json.getJson());
        assertThat(parsed.getObject()).isEqualTo(subject);
    }


    @Test
    void shouldSerializeAnonymousIndividual() throws IOException {
        var subject = dataFactory.getOWLAnonymousIndividual();
        var json = tester.write(subject);
        System.out.println(json.getJson());
        var parsed = tester.parse(json.getJson());
        assertThat(parsed.getObject()).isEqualTo(subject);
    }

}
