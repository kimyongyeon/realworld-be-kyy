package com.real.worldkyy.restController

import com.real.worldkyy.common.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexRestController {

    @GetMapping("/")
    fun index(): ApiResponse = ApiResponse.ok("world")

}