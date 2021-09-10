package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDatatype;
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
public class OWLClass_Serialization_TestCase {

    @Autowired
    private JacksonTester<OWLClass> tester;

    @Autowired
    private OWLDataFactory dataFactory;

    @Test
    void shouldSerializeClass() throws IOException {
        var iri = IRI.create("http://example.org/x");
        var cls = dataFactory.getOWLClass(iri);
        var written = tester.write(cls);
        var json = written.getJson();
        System.out.println(json);

        assertThat(written).extractingJsonPathValue("['@type']").isEqualTo("Class");
        assertThat(written).extractingJsonPathValue("iri").isEqualTo(iri.toString());


        var parsed = tester.parse(json);
        var parsedCls = parsed.getObject();
        assertThat(parsedCls).isEqualTo(cls);
    }

    @Test
    void shouldDeserializeClass() throws IOException {
        var iri = IRI.create("http://example.org/x");
        var cls = dataFactory.getOWLClass(iri);
        var json = """
                {"@type" : "Class", "iri" : "http://example.org/x"}
                """;
        var parsed = tester.parse(json);
        var parsedCls = parsed.getObject();
        assertThat(parsedCls).isEqualTo(cls);
    }
}
