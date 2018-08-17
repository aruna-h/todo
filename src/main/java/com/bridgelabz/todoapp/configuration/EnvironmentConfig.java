package com.bridgelabz.todoapp.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author bridgelabz
 * @since 31/07/2018 <br>
 *        <p>
 *        Entity giving info about Multiple Environment setup configuration <br>
 *        </p>
 */
public class EnvironmentConfig {
	public static final Logger logger = LoggerFactory.getLogger(EnvironmentConfig.class);

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		Resource resource;
		String activeProfile;

		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();

		// get active profile
		activeProfile = System.getProperty("spring.profiles.active");

		// choose different property files for different active profile
		if ("development".equals(activeProfile)) {
			resource = new ClassPathResource("/META_INF/development.properties");
			logger.info(activeProfile + " profile selected");
		} else if ("test".equals(activeProfile)) {
			resource = new ClassPathResource("/META_INF/test.properties");
			logger.info(activeProfile + " profile selected");
		} else {
			resource = new ClassPathResource("/META_INF/production.properties");
			logger.info(activeProfile + " profile selected");
		}

		// load the property file
		propertySourcesPlaceholderConfigurer.setLocation(resource);

		return propertySourcesPlaceholderConfigurer;
	}

}
