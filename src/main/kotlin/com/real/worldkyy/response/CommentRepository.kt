package com.real.worldkyy.response

import com.real.worldkyy.domain.Comments
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comments, Long> {
}