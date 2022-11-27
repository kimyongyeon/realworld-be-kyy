package com.real.worldkyy.service.articles

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ArticleService @Autowired constructor(
    private val query: JPAQueryFactory
){
}