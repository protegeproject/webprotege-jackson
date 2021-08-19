package edu.stanford.protege.webprotege.jackson;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.semanticweb.owlapi.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;

@SpringBootApplication
public class WebprotegeOwlApiJacksonApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebprotegeOwlApiJacksonApplication.class, args);
	}

	@Bean
	@ConditionalOnMissingBean
	OWLDataFactory dataFactory() {
		return new OWLDataFactoryImpl();
	}

	@Bean
	GuavaModule guavaModule() {
		return new GuavaModule();
	}

	@Bean
	public ObjectMapper objectMapper(OWLDataFactory dataFactory) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
		mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.registerModule(new JavaTimeModule());
		mapper.registerModule(new Jdk8Module());
		mapper.registerModule(new GuavaModule());
		SimpleModule module = new SimpleModule();
		module.addSerializer(OWLEntity.class, new OWLEntitySerializer());
		module.addSerializer(OWLProperty.class, new OWLEntitySerializer());
		module.addSerializer(new EntityTypeSerializer());
		module.addDeserializer(EntityType.class, new EntityTypeDeserializer());
		module.addDeserializer(OWLEntity.class, new OWLEntityDeserializer<>(dataFactory));
		module.addDeserializer(OWLNamedIndividual.class, new OWLEntityDeserializer<>(dataFactory));
		module.addDeserializer(OWLProperty.class, new OWLPropertyDeserializer<>(dataFactory));
		module.addDeserializer(OWLObjectProperty.class, new OWLPropertyDeserializer<>(dataFactory));
		module.addDeserializer(OWLDataProperty.class, new OWLPropertyDeserializer<>(dataFactory));
		module.addDeserializer(OWLAnnotationProperty.class, new OWLPropertyDeserializer<>(dataFactory));
		module.addDeserializer(OWLDatatype.class, new OWLEntityDeserializer<>(dataFactory));
		module.addDeserializer(OWLClass.class, new OWLClassDeserializer(dataFactory));
		module.addDeserializer(IRI.class, new IriDeserializer());
		module.addDeserializer(OWLAnnotationValue.class, new OWLAnnotationValueDeserializer(new OWLLiteralDeserializer(dataFactory),
																							new IriDeserializer()));
		module.addSerializer(OWLLiteral.class, new OWLLiteralSerializer());
		module.addDeserializer(OWLLiteral.class, new OWLLiteralDeserializer(dataFactory));
		module.addSerializer(IRI.class, new IriSerializer());
		module.addSerializer(OWLOntologyID.class, new OWLOntologyIDSerializer());
		module.addDeserializer(OWLOntologyID.class, new OWLOntologyIDDeserializer());

		module.addSerializer(AxiomType.class, new AxiomTypeSerializer());
		module.addDeserializer(AxiomType.class, new AxiomTypeDeserializer());

		mapper.registerModule(module);

		return mapper;
	}
}
