package com.real.worldkyy.restController.customer

import com.real.worldkyy.common.ApiResponse
import com.real.worldkyy.common.TokenValidationInterceptor
import com.real.worldkyy.common.auth.JWTUtil
import com.real.worldkyy.dto.SigninRequestDTO
import com.real.worldkyy.dto.SignupRequestDTO
import com.real.worldkyy.service.auth.UserContextHolder
import com.real.worldkyy.service.customer.SigninService
import com.real.worldkyy.service.customer.SignupService
import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.service.GrantType
import java.lang.IllegalArgumentException

// https://jjeda.tistory.com/24 : 생성자 주입도 롬복이 없어서 뜻대로 안된다. 더 아름다운 방법이 있을텐데...
@Api(tags = ["회원정보"])
@RestController
@RequestMapping("/api/v1")
class CustomerRestController @Autowired constructor(
    private val signupService: SignupService,
    private val signinService: SigninService,
    private val userContextHolder: UserContextHolder
) {
    /**
     * 회원가입
     */
    @PostMapping("/users")
    fun signup(@RequestBody signupDTO: SignupRequestDTO) = ApiResponse.ok(signupService.signup(signupDTO))

    /**
     * 로그인
     */
    @PostMapping("/signin")
    fun signin(@RequestBody signinDTO: SigninRequestDTO) = ApiResponse.ok(signinService.signin(signinDTO))

    @GetMapping("/refresh_token")
    fun refreshToken(@RequestParam("grant_type") grantType: String): ApiResponse {
        if (grantType != TokenValidationInterceptor.GRANT_TYPE_REFRESH) {
            throw IllegalArgumentException("grant_type 없음")
        }

        return userContextHolder.email?.let {
            ApiResponse.ok(JWTUtil.createToken(it))
        } ?: throw IllegalArgumentException("사용자 정보가 없음")

    }


}