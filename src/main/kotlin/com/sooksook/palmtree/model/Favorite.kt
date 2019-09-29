package com.sooksook.palmtree.model

import java.time.LocalDateTime
import javax.persistence.*
import javax.persistence.Id

@Entity
data class Favorite(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        val id: Long,
        @Column(nullable = false)
        val userId: String,
        @Column(nullable = false)
        val plantId: Long,
        @Column(nullable = false)
        val createdAt: LocalDateTime
)