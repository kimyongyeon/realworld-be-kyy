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

        val list = listOf(1,2,3,4)
        val toList = list.map { item -> item * 2 }
            .filter { item -> item > 2 }
            .toList()

        val multi = {x: Int, y: Int -> x + y}

        fun calc(a: Int, b: Int, callback: (x: Int, y: Int) -> Unit) {
            callback(a, b)
        }
        calc(1, 2, {a, b -> a + b })


    }
}

fun main(args: Array<String>) {
    runApplication<RealworldBeKyyApplication>(*args)
}


