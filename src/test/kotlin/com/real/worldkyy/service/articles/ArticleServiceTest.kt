package com.real.worldkyy.service.articles

import com.querydsl.jpa.impl.JPAQueryFactory
import com.real.worldkyy.domain.Articles
import com.real.worldkyy.domain.Customer
import com.real.worldkyy.domain.QArticles
import com.real.worldkyy.domain.QArticles.articles
import com.real.worldkyy.domain.Tags
import com.real.worldkyy.response.ArticlesRepository
import com.real.worldkyy.response.CustomerRepository
import com.real.worldkyy.response.TagsRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArticleServiceTest constructor(
    @Autowired
    private val articleRepository: ArticlesRepository,
    @Autowired
    private val tagsRepository: TagsRepository,
    @Autowired
    private val customerRepository: CustomerRepository,
    @Autowired
    private val query: JPAQueryFactory
) {
    @Test
    fun createArticleTest() {
        val customer = Customer(username = "김샌다. ", email = "test@naver.com", password = "1234")
        customerRepository.save(customer)
        val tags = Tags(tagName = "누구냐 넌? ")
        tagsRepository.save(tags)

        val articles =
            Articles(title = "누가 좀 해봐", description = "내용은 뭔데?", body = "본론은 뭔데? ", cutomer = customer, tags = tags)
        articleRepository.save(articles)
    }

    @Test
    fun deleteArticleTest() {
        val fetchFirst = query.from(articles)
            .where(articles.title.eq("누가 좀 해봐")).fetchFirst()
        articleRepository.delete(fetchFirst as Articles)
    }

    @Test
    fun whereDescriptionTest() {
        val fetchFirst = query.from(articles).where(articles.description.eq("뭔데?"))
            .limit(10)
            .fetchFirst()
        print(fetchFirst)
    }


}