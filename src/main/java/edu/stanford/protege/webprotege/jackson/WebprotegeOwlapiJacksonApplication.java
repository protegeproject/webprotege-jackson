package edu.stanford.protege.webprotege.jackson;

import org.semanticweb.owlapi.model.OWLDataFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.Conditional;
import uk.ac.manchester.cs.owl.owlapi.OWLDataFactoryImpl;

import javax.naming.spi.ObjectFactory;

@SpringBootApplication
public class WebprotegeOwlapiJacksonApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebprotegeOwlapiJacksonApplication.class, args);
	}

	@Bean
	@ConditionalOnMissingBean
	OWLDataFactory dataFactory() {
		return new OWLDataFactoryImpl();
	}
}
