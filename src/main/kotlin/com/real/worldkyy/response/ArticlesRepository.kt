package com.real.worldkyy.response

import com.real.worldkyy.domain.Articles
import org.springframework.data.jpa.repository.JpaRepository

interface ArticlesRepository : JpaRepository<Articles, Long>