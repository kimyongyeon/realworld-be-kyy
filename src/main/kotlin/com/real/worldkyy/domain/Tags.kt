package com.real.worldkyy.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Tags (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val tagNo: Long,
    private val tagName: String
) : BaseEntity() {}