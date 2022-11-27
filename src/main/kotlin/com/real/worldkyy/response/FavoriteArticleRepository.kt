package com.real.worldkyy.response

import com.real.worldkyy.domain.FavoriteArticle
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteArticleRepository : JpaRepository<FavoriteArticle, Long> {
}