package com.real.worldkyy.domain

import javax.persistence.*

@Entity
data class Articles (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val articlesNo: Long = 0,
    private val title: String = "",
    private val description: String = "",
    private val body: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    private val cutomer: Customer,

    @ManyToOne(fetch = FetchType.LAZY)
    private val tags: Tags? = null
) : BaseEntity() {}



