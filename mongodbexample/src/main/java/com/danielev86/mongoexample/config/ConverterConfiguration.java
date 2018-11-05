package com.danielev86.mongoexample.config;

import java.util.Set;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;

import com.danielev86.mongoexample.converter.DocumentToActorConverter;
import com.danielev86.mongoexample.converter.DocumentToMovieConverter;
import com.danielev86.mongoexample.converter.MovieToDocumentConverter;

@Configuration
public class ConverterConfiguration extends ConversionServiceFactoryBean {

    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        ConversionService conversionService = getObject();
        ConverterRegistry registry = (ConverterRegistry) conversionService;
        // register converters that need a nested conversion service
        //
        registry.addConverter(new DocumentToMovieConverter(conversionService));
        registry.addConverter(new MovieToDocumentConverter());
        registry.addConverter(new DocumentToActorConverter());
    }
   
	
}
