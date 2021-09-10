package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
public class IRI_Serialization_TestCase {

    public static final String IRI_STRING = "http://example.org/A";

    @Autowired
    private JacksonTester<IRI> tester;

    @Test
    void shouldWriteIri() throws IOException {
        var json = tester.write(IRI.create(IRI_STRING));
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathValue("['@type']").isEqualTo("IRI");
        assertThat(json).extractingJsonPathValue("value").isEqualTo(IRI_STRING);
    }

//    @Test
//    void shouldReadIriFromJsonString() throws IOException {
//        var parsed = tester.parse("\"" + IRI_STRING + "\"");
//        var parsedIri = parsed.getObject();
//        assertThat(parsedIri.toString()).isEqualTo(IRI_STRING);
//    }

    @Test
    void shouldReadIriFromJsonObject() throws IOException {
        var parsed = tester.parse("""
                                            {
                                                "@type" : "IRI",
                                                "value" : "http://example.org/A"
                                            }
                                          """);
        var parsedIri = parsed.getObject();
        assertThat(parsedIri.toString()).isEqualTo(IRI_STRING);
    }
}
