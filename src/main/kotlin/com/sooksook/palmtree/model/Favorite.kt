package com.sooksook.palmtree.model

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@IdClass(FavoriteId::class)
data class Favorite(
        @Id
        val userId: String,
        @Id
        val plantId: PlantId,
        @Column(nullable = false)
        val createdAt: LocalDateTime
)

data class FavoriteId(
        val userId: String = "",
        val plantId: PlantId = ""
): Serializable
