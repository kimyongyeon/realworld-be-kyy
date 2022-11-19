package com.real.worldkyy.response

import com.real.worldkyy.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, String> {

    // 리턴타입을 주지 않으면 Unit 타입으로 Void 형식이라 리턴값이 없다. ㅡㅡ 반드시 리턴타입을 설정하자.
    fun findByEmail(email: String): Customer?
}