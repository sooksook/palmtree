package com.sooksook.palmtree.repository

import com.sooksook.palmtree.model.Favorite
import com.sooksook.palmtree.model.FavoriteId
import com.sooksook.palmtree.model.PlantId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FavoriteRepository: JpaRepository<Favorite, FavoriteId> {
    @Query("select f from Favorite f where f.userId = ?1")
    fun findAllByUserId(userId: String): List<Favorite>
}
