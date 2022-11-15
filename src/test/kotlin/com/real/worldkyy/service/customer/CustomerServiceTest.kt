package com.real.worldkyy.service.customer

import com.real.worldkyy.domain.Customer
import com.real.worldkyy.response.CustomerRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class CustomerServiceTest @Autowired constructor(
    // Autowired를 안하면 주입이 되지 않음.
    val customerService: CustomerService,
    val customerRepository: CustomerRepository
) {

    @Test
    fun usernameTest() {
        val findUserName = customerService.findUserName("kkk")
        print(findUserName)
    }

    @Test
    fun customerFindEmailTest() {
        val email = customerService.findEmail("test@naver.com")
        print(email);
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