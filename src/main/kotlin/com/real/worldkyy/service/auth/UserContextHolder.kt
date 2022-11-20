package com.real.worldkyy.service.auth

import com.real.worldkyy.response.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserContextHolder @Autowired constructor(
    private val customerRepository: CustomerRepository
) {
    private val userHolder = ThreadLocal.withInitial {
        UserHolder()
    }

    val id: Long? get() = userHolder.get().id
    val name: String? get() = userHolder.get().name
    val email: String? get() = userHolder.get().email

    fun set(email: String) = customerRepository
        .findByEmail(email)?.let { user ->
            this.userHolder.get().apply {
                this.name = user.username
                this.email = user.email
            }.run(userHolder::set)
        }

    fun clear() {
        userHolder.remove()
    }

    class UserHolder {
        var id: Long? = null
        var email: String? = null
        var name: String? = null
    }
}