package com.perkpal.perkpal_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class MongoConfig {

    @Bean
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(new LocalDateToStringConverter(), new StringToLocalDateConverter()));
    }

    // Converter to convert LocalDate to String
    public static class LocalDateToStringConverter implements Converter<LocalDate, String> {
        @Override
        public String convert(LocalDate source) {
            return source != null ? source.toString() : null;  // Converts LocalDate to String in "yyyy-MM-dd" format
        }
    }

    // Converter to convert String to LocalDate
    public static class StringToLocalDateConverter implements Converter<String, LocalDate> {
        @Override
        public LocalDate convert(String source) {
            return source != null ? LocalDate.parse(source, DateTimeFormatter.ISO_DATE) : null;  // Converts String to LocalDate
        }
    }
}
