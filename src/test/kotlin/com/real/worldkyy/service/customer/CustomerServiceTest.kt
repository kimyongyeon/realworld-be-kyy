package com.real.worldkyy.service.customer

import com.querydsl.jpa.impl.JPAQueryFactory
import com.real.worldkyy.domain.Customer
import com.real.worldkyy.domain.QCustomer.customer
import com.real.worldkyy.response.CustomerRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CustomerServiceTest @Autowired constructor(
    // Autowired를 안하면 주입이 되지 않음.
    val customerRepository: CustomerRepository,
    val query: JPAQueryFactory
) {

    @Test
    fun usernameTest() {
        val fetch = query.select(customer)
            .from(customer)
            .where(customer.username.eq("hello"))
            .fetch()
        print(fetch)
    }

    @Test
    fun customerFindEmailTest() {
        val fetch = query.select(customer)
            .from(customer)
            .where(customer.email.eq("test@naver.com"))
            .fetch()
        print(fetch)
    }

    @Test
    fun customerInsertTest() {
        val customer = Customer(
            email = "test@naver.com",
            bio = "bin test",
            username = "test name",
            password = "1234",
            image = "http://naver.com"
        )
        customerRepository.save(customer)
    }
}