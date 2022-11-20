package com.real.worldkyy.service.customer

import com.real.worldkyy.common.RealworldException
import com.real.worldkyy.domain.Customer
import com.real.worldkyy.dto.SignupRequestDTO
import com.real.worldkyy.response.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service

@Service
class SignupService @Autowired constructor (val customerRepository: CustomerRepository) {
    fun signup(signupDTO: SignupRequestDTO) {
        print(signupDTO)
        validateRequest(signupDTO)
        checkDuplicates(signupDTO.email)
        registerUser(signupDTO)
    }

    private fun registerUser(signupDTO: SignupRequestDTO) {
        with(signupDTO) {
            val hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
            val user = Customer(email = email, username = name, password = hashedPassword)
            customerRepository.save(user)
        }
    }

    private fun checkDuplicates(email: String) =
        customerRepository.findByEmail(email)?.let {
            throw RealworldException("이미 사용 중인 이메일입니다.")
        }

    private fun validateRequest(signupDTO: SignupRequestDTO) {
        validateEmail(signupDTO.email)
        validateName(signupDTO.name)
        validatePassword(signupDTO.password)
    }

    private fun validatePassword(password: String) {
        if (password.trim().length !in 8..20) {
            throw RealworldException("비밀번호는 공백을 제외하고 8자 이상 20자 이하여야 합니다.")
        }
    }

    private fun validateName(name: String) {
        if (name.trim().length !in 2..20) {
            throw RealworldException("이름은 2자 이상 20자 이하여야 합니다.")
        }
    }

    private fun validateEmail(email: String) {
        val isNotValidEmail = "^[A-Z0-9.%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
            .toRegex(RegexOption.IGNORE_CASE)
            .matches(email)
            .not()

        if (isNotValidEmail) {
            throw RealworldException("이메일 형식이 올바르지 않습니다.")
        }
    }
}