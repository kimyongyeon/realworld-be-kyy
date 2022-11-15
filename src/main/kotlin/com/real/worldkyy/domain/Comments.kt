package com.real.worldkyy.domain

import javax.persistence.*

@Entity
data class Comments(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val commentNo: Long,
    private val body: String,

    @ManyToOne(fetch = FetchType.LAZY)
    private val articles: Articles
) : BaseEntity() {}