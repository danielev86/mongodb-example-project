package com.danielev86.mongoexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import com.danielev86.mongoexample.converter.MovieConverter;

@Configuration
public class ConverterConfiguration {

	@Bean	
	public ConversionService conversionService() {
		DefaultConversionService conversionService = new DefaultConversionService();
		conversionService.addConverter(new MovieConverter());
		return conversionService;
	}
	
}
