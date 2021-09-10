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
import uk.ac.manchester.cs.owl.owlapi.*;

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
//		module.addSerializer(OWLProperty.class, new OWLEntitySerializer());
		module.addSerializer(new EntityTypeSerializer());
		module.addDeserializer(EntityType.class, new EntityTypeDeserializer());
		module.addDeserializer(OWLEntity.class, new OWLEntityDeserializer<>(dataFactory));
		module.addDeserializer(OWLAnnotationSubject.class, new OWLAnnotationSubjectDeserializer());
		module.addDeserializer(OWLNamedIndividual.class, new OWLEntityDeserializer<>(dataFactory));
		module.addDeserializer(OWLProperty.class, new OWLPropertyDeserializer<>(dataFactory));
		module.addDeserializer(OWLObjectProperty.class, new OWLPropertyDeserializer<>(dataFactory));
		module.addDeserializer(OWLDataProperty.class, new OWLPropertyDeserializer<>(dataFactory));
		module.addDeserializer(OWLAnnotationProperty.class, new OWLPropertyDeserializer<>(dataFactory));
//		module.addDeserializer(OWLDatatype.class, new OWLEntityDeserializer<>(dataFactory));
		module.addDeserializer(OWLClass.class, new OWLClassDeserializer(dataFactory));
		module.addDeserializer(OWLNamedIndividual.class, new OWLNamedIndividualDeserializer(new OWLEntityDeserializer<>(dataFactory)));
//		module.addDeserializer(IRI.class, new IriDeserializer());
		module.addDeserializer(OWLAnnotationValue.class, new OWLAnnotationValueDeserializer(new OWLLiteralDeserializer(dataFactory),
																							new IriDeserializer()));

		module.setMixInAnnotation(OWLAnnotationSubject.class, OWLAnnotationSubjectMixin.class);


		module.addSerializer(OWLLiteral.class, new OWLLiteralSerializer());
		module.addDeserializer(OWLLiteral.class, new OWLLiteralDeserializer(dataFactory));
		module.addSerializer(IRI.class, new IriSerializer());
		module.addSerializer(OWLOntologyID.class, new OWLOntologyIDSerializer());
		module.addDeserializer(OWLOntologyID.class, new OWLOntologyIDDeserializer());

		module.setMixInAnnotation(NodeID.class, NodeIDMixin.class);


		module.addSerializer(AxiomType.class, new AxiomTypeSerializer());
		module.addDeserializer(AxiomType.class, new AxiomTypeDeserializer());

//		module.addSerializer(OWLAxiom.class, new OWLAxiomSerializer());
//		module.addDeserializer(OWLAxiom.class, new OWLAxiomDeserializer(dataFactory));
//
		module.setMixInAnnotation(OWLAxiom.class, OWLAxiomMixin.class);

		module.setMixInAnnotation(OWLSubClassOfAxiom.class, OWLSubClassOfAxiomMixin.class);
		module.setMixInAnnotation(OWLSubClassOfAxiomImpl.class, OWLSubClassOfAxiomImplMixin.class);

		module.setMixInAnnotation(OWLEquivalentClassesAxiom.class, OWLEquivalentClassesAxiomMixin.class);
		module.setMixInAnnotation(OWLEquivalentClassesAxiomImpl.class, OWLEquivalentClassesAxiomImplMixin.class);

		module.setMixInAnnotation(OWLDisjointClassesAxiom.class, OWLDisjointClassesAxiomMixin.class);
		module.setMixInAnnotation(OWLDisjointClassesAxiomImpl.class, OWLDisjointClassesAxiomImplMixin.class);

		module.setMixInAnnotation(OWLAnnotationAssertionAxiom.class, OWLAnnotationAssertionAxiomMixin.class);
		module.setMixInAnnotation(OWLAnnotationAssertionAxiomImpl.class, OWLAnnotationAssertionAxiomImplMixin.class);

		module.setMixInAnnotation(OWLClassAssertionAxiom.class, OWLClassAssertionAxiomMixin.class);
		module.setMixInAnnotation(OWLClassAssertionAxiomImpl.class, OWLClassAssertionAxiomImplMixin.class);

		module.setMixInAnnotation(OWLDeclarationAxiom.class, OWLDeclarationAxiomMixin.class);
		module.setMixInAnnotation(OWLDeclarationAxiomImpl.class, OWLDeclarationAxiomImplMixin.class);

//		module.setMixInAnnotation(OWLEntity.class, OWLEntityMixin.class);
//
		module.setMixInAnnotation(OWLClass.class, OWLClassMixin.class);
//		module.setMixInAnnotation(OWLClassImpl.class, OWLClassImplMixin.class);
//
		module.setMixInAnnotation(OWLDatatype.class, OWLDatatypeMixin.class);
		module.setMixInAnnotation(OWLDatatypeImpl.class, OWLDatatypeImplMixin.class);
//
		module.setMixInAnnotation(OWLObjectProperty.class, OWLObjectPropertyMixin.class);
		module.setMixInAnnotation(OWLObjectPropertyImpl.class, OWLObjectPropertyImplMixin.class);
//
		module.setMixInAnnotation(OWLDataProperty.class, OWLDataPropertyMixin.class);
		module.setMixInAnnotation(OWLDataPropertyImpl.class, OWLDataPropertyImplMixin.class);
//
		module.setMixInAnnotation(OWLAnnotationProperty.class, OWLAnnotationPropertyMixin.class);
		module.setMixInAnnotation(OWLAnnotationPropertyImpl.class, OWLAnnotationPropertyImplMixin.class);
//
		module.setMixInAnnotation(OWLNamedIndividual.class, OWLNamedIndividualMixin.class);
//		module.setMixInAnnotation(OWLNamedIndividualImpl.class, OWLNamedIndividualImplMixin.class);

		module.setMixInAnnotation(OWLIndividual.class, OWLIndividualMixin.class);

		module.setMixInAnnotation(OWLAnonymousIndividual.class, OWLAnonymousIndividualMixin.class);
		module.setMixInAnnotation(OWLAnonymousIndividualImpl.class, OWLAnonymousIndividualImplMixin.class);

		module.setMixInAnnotation(OWLObjectInverseOf.class, OWLObjectInverseOfMixin.class);
		module.setMixInAnnotation(OWLObjectInverseOfImpl.class, OWLObjectInverseOfMixin.class);

		module.setMixInAnnotation(OWLClassExpression.class, OWLClassExpressionMixin.class);

		module.setMixInAnnotation(IRI.class, IRIMixin.class);

		mapper.addHandler(new IriDeserializationProblemHandler());


		mapper.registerModule(module);

		return mapper;
	}
}
