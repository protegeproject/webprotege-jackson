package edu.stanford.protege.webprotege.jackson;

import org.junit.jupiter.api.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-04-06
 */
@JsonTest
public class OWLOntologyIDSerialization_TestCase {

    @Autowired
    private JacksonTester<OWLOntologyID> tester;

    @Test
    public void shouldSerializeOntologyWithOntologyIri() throws IOException {
        var ontologyIri = "http://example.org/ont";
        var ontologyId = new OWLOntologyID(IRI.create(ontologyIri));
        var json = tester.write(ontologyId);
        assertThat(json).extractingJsonPathStringValue("iri").isEqualTo(ontologyIri);
        assertThat(json).doesNotHaveJsonPath("versionIri");
    }

    @Test
    public void shouldSerializeOntologyWithOntologyIriAndVersionIri() throws IOException {
        var ontologyIri = "http://example.org/ont";
        var versionIri = "http://example.org/ver";
        var ontologyId = new OWLOntologyID(IRI.create(ontologyIri), IRI.create(versionIri));
        var json = tester.write(ontologyId);
        System.out.println(json.getJson());
        assertThat(json).extractingJsonPathStringValue("iri").isEqualTo(ontologyIri);
        assertThat(json).extractingJsonPathStringValue("versionIri").isEqualTo(versionIri);

    }
}
