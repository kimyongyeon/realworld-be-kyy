package com.real.worldkyy.common

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice
@RestController
class RealWorldExceptionHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(RealworldException::class)
    fun handleRealworldException(e: RealworldException): ApiResponse {
        logger.error("Api Error", e)
        return ApiResponse.error(e.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ApiResponse {
        logger.error("Api Error", e)
        return ApiResponse.error("알 수 없는 오류가 발생했습니다.")
    }
}