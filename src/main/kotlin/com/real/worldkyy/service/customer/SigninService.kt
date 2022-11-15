package com.real.worldkyy.service.customer

import com.real.worldkyy.common.RealworldException
import com.real.worldkyy.common.auth.JWTUtil
import com.real.worldkyy.domain.Customer
import com.real.worldkyy.dto.SigninDTO
import com.real.worldkyy.dto.SigninResponseDTO
import com.real.worldkyy.response.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class SigninService @Autowired constructor(private val customerRepository: CustomerRepository) {

    fun signin(signinDTO: SigninDTO): SigninResponseDTO {
        val user = customerRepository.findByEmail(signinDTO.email.lowercase())
            ?: throw RealworldException("로그인 정보를 확인해 주세요.")

        if (isNotValidPassword(signinDTO.password, user.password)) {
            throw RealworldException("로그인 정보를 확인해 주세요.")
        }
        return responseWithTokens(user)
    }

    private fun isNotValidPassword(plain: String, hashed: String) = BCrypt.checkpw(plain, hashed).not()

    private fun responseWithTokens(user: Customer) = user.email?.let { email ->
        SigninResponseDTO(
            JWTUtil.createToken(user.email),
            JWTUtil.createRefreshToken(user.email),
            user.username,
            email
        )
    }

}