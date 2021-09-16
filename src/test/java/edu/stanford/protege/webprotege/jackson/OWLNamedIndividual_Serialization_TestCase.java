package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-13
 */
@JsonTest
public class OWLNamedIndividual_Serialization_TestCase {


    @Autowired
    private JacksonTester<OWLNamedIndividual> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    @Test
    void shouldSerializeNamedIndividual() throws IOException {
        var iri = IRI.create("http://example.org/x");
        var cls = dataFactory.getOWLNamedIndividual(iri);
        var written = tester.write(cls);
        var json = written.getJson();
        System.out.println(json);

        assertThat(written).extractingJsonPathValue("['@type']").isEqualTo("NamedIndividual");
        assertThat(written).extractingJsonPathValue("iri").isEqualTo(iri.toString());


        var parsed = tester.parse(json);
        var parsedInd = parsed.getObject();
        assertThat(parsedInd).isEqualTo(cls);
    }

    @Test
    void shouldDeserializeNamedIndividual() throws IOException {
        var iri = IRI.create("http://example.org/x");
        var cls = dataFactory.getOWLNamedIndividual(iri);
        var json = """
                {"@type" : "NamedIndividual", "iri" : "http://example.org/x"}
                """;
        var parsed = tester.parse(json);
        var parsedInd = parsed.getObject();
        assertThat(parsedInd).isEqualTo(cls);
    }

    @Test
    void shouldDeserializeNamedIndividualWithLegacyTypeNames() throws IOException {
        var iri = IRI.create("http://example.org/x");
        var cls = dataFactory.getOWLNamedIndividual(iri);
        var json = """
                {"type" : "owl:NamedIndividual", "iri" : "http://example.org/x"}
                """;
        var parsed = tester.parse(json);
        var parsedInd = parsed.getObject();
        assertThat(parsedInd).isEqualTo(cls);
    }
}
