package com.sooksook.palmtree.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: String,
        @Column(nullable = false)
        val nickname: String,
        @Column(nullable = false)
        val createdAt: LocalDateTime
)