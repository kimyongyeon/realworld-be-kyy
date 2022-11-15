package com.real.worldkyy.dto

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class CustomerDTO (

    var email: String,

    username: String?,
    password: String?,
    authorities: Collection<GrantedAuthority?>?

) : User (username, password, authorities) {
    init {
        this.email = username.orEmpty()
    }
}