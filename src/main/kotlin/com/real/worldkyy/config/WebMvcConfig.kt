package com.real.worldkyy.config

import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WebMvcConfig {
    @Bean
    fun modelMapper(): ModelMapper? {
        return ModelMapper()
    }
}