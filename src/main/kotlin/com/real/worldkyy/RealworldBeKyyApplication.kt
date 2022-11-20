package com.real.worldkyy

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class RealworldBeKyyApplication : ApplicationRunner{
    override fun run(args: ApplicationArguments?) {
        fun findUsername(username: String) = "select * from Customer where username = ${username}"
        val resultUsername = findUsername("hello")

        val username = "hello"
        val email = "test@naver.com"
        val query = """
            select * 
            from Customer 
            where username = ${username}
            and email = ${email}
        """.trimIndent()
    }
}

fun main(args: Array<String>) {
    runApplication<RealworldBeKyyApplication>(*args)
}


