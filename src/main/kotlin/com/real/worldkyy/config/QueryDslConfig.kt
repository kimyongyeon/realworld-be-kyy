package com.real.worldkyy.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
class QueryDslConfig constructor(@PersistenceContext private val em: EntityManager) {
    @Bean
    fun query(): JPAQueryFactory {
        return JPAQueryFactory(em)
    }
}