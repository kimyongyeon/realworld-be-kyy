package com.real.worldkyy.domain

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id

@Entity
data class Customer (
    @Id
    val email: String = "",
    val username: String = "",
    val password: String = "",
    var bio: String = "",
    var image: String = "",


) : BaseEntity()