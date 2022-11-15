package com.real.worldkyy.domain

import javax.persistence.*

@Entity
class FavoriteArticle(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val favolriteArticleNo: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    private val articles: Articles
) : BaseEntity() {}