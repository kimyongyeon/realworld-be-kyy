package com.real.worldkyy.domain

import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id

enum class CustomerRole {
    GUEST, USER, MEMBER, MANAGER, ADMIN
}

@Entity
data class Customer (
    @Id
    val email: String,
    val username: String,
    val password: String,
    var bio: String = "",
    var image: String = "",

    @ElementCollection(fetch = FetchType.LAZY)
    val roleSet: Set<CustomerRole> = HashSet()

) : BaseEntity() {
    fun addMemberRole(customerRole: CustomerRole) {
        roleSet.plus(customerRole)
    }
}