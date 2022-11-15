package com.real.worldkyy.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter

@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Bean
    fun passwordEncoder(): PasswordEncoder? = BCryptPasswordEncoder()

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        // 사용자 계정은 user1
        auth.inMemoryAuthentication().withUser("user1") // 1111 패스워드 인코딩 결과
            .password(BCryptPasswordEncoder().encode("1234"))
            .roles("USER")
    }

    // 인가
    @Throws(java.lang.Exception::class)
    override fun configure(http: HttpSecurity) {
        // https://github.com/HomoEfficio/dev-tips/blob/master/Spring%20Security%EC%99%80%20h2-console%20%ED%95%A8%EA%BB%98%20%EC%93%B0%EA%B8%B0.md
        http.authorizeRequests()
            .antMatchers("/sample/all").permitAll()
            .antMatchers("/api/v1/**").permitAll() // 회원가입
            .antMatchers("/sample/member").hasRole("USER")
            .antMatchers("/sample/admin")
            .hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .headers()
            .addHeaderWriter(XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))

        http.formLogin() // 인증/인가 문제시 로그인 화면 이동
        http.csrf().disable() // get 방식의 logout도 허용
        http.logout() // 로그아웃 설정
    }
}