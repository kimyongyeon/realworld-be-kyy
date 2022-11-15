package com.real.worldkyy.domain

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

// https://dico.me/back-end/articles/308/ko
// BaseEntity는 DataClass로 하면 안됨 ㅠㅠ 추상클래스로 만들어서 상속해야 하는걸 알아냈다.
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class) // 엔티티 변경되는거 감지(생성, 변경)
abstract class BaseEntity {
    @CreatedDate
    @Column(name = "regDate", updatable = false, nullable = false)
    private var regDate: LocalDateTime = LocalDateTime.MIN // val 아닌 var로 해야만 insert문이 처리 되었다. 이유는 뭘까?

//    @CreatedBy
//    private val regName: String = "admin_reg"

    @LastModifiedDate
    @Column(name = "modDate", nullable = false)
    private var modDate: LocalDateTime = LocalDateTime.MIN

//    @LastModifiedBy
//    private val modName: String = "admin_edit"
}