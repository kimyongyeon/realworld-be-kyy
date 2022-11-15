package com.real.worldkyy.restController.customer

import com.real.worldkyy.common.ApiResponse
import com.real.worldkyy.dto.SigninDTO
import com.real.worldkyy.dto.SigninResponseDTO
import com.real.worldkyy.dto.SignupDTO
import com.real.worldkyy.service.customer.CustomerService
import com.real.worldkyy.service.customer.SigninService
import com.real.worldkyy.service.customer.SignupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// https://jjeda.tistory.com/24 : 생성자 주입도 롬복이 없어서 뜻대로 안된다. 더 아름다운 방법이 있을텐데...
@RestController
@RequestMapping("/api/v1")
class CustomerRestController @Autowired constructor(
    private val signupService: SignupService,
    private val signinService: SigninService
) {
    @PostMapping("/users")
    fun signup(@RequestBody signupDTO: SignupDTO) = ApiResponse.ok(signupService.signup(signupDTO))

    @PostMapping("/signin")
    fun signin(@RequestBody signinDTO: SigninDTO) = ApiResponse.ok(signinService.signin(signinDTO))



}