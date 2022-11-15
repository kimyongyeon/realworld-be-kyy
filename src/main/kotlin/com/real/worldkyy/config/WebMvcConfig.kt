package com.real.worldkyy.config

import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry

@Configuration
class WebMvcConfig {
    private val CLASSPATH_RESOURCE_LOCATIONS = arrayOf(
        "classpath:/static/", "classpath:/public/", "classpath:/",
        "classpath:/resources/", "classpath:/META-INF/resources/", "classpath:/META-INF/resources/webjars/"
    )

    fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/").setViewName("forward:/index")
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
    }

//    @Bean
//    fun modelMapper(): ModelMapper? {
//        return ModelMapper()
//    }
}