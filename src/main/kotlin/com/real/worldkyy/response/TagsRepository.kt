package com.real.worldkyy.response

import com.real.worldkyy.domain.Tags
import org.springframework.data.jpa.repository.JpaRepository

interface TagsRepository: JpaRepository<Tags, Long> {
}