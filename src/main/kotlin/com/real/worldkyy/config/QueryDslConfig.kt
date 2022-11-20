package com.real.worldkyy.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager

@Configuration
class QueryDslConfig constructor(@Autowired private val em: EntityManager) {
    @Bean
    fun query(): JPAQueryFactory {
        return JPAQueryFactory(em)
    }
}