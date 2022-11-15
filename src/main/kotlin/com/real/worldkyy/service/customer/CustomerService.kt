package com.real.worldkyy.service.customer

import com.querydsl.jpa.impl.JPAQueryFactory
import com.real.worldkyy.domain.Customer
import com.real.worldkyy.domain.QCustomer.customer
import com.real.worldkyy.dto.CustomerDTO
import com.real.worldkyy.response.CustomerRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CustomerService @Autowired constructor(
    val repository: CustomerRepository,
    val query: JPAQueryFactory
) : UserDetailsService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun findUserName(name: String): MutableList<Customer>? {
        return query.select(customer).from(customer)
            .where(customer.username.eq(name))
            .fetch();
    }

    override fun loadUserByUsername(username: String): CustomerDTO? {

        logger.info("CustomerService loadUserByUsername : " + username)
        val customer: Customer? = repository.findByEmail(username)

        customer?.let {
            throw UsernameNotFoundException("check Email")
        }

        logger.info("===================================")
        logger.info(customer.toString())
        val customerDTO = customer?.let {
            CustomerDTO(
                email = it.email,
                password = customer.password,
                authorities = customer.roleSet.stream()
                    .map { role -> SimpleGrantedAuthority("ROLE_" + role.name) }
                    .collect(Collectors.toSet()
                ),
                username = customer.username
            )
        }
        return customerDTO
    }
}