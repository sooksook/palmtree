package com.sooksook.palmtree.controllers

import com.sooksook.palmtree.model.Favorite
import com.sooksook.palmtree.model.FavoriteId
import com.sooksook.palmtree.model.PlantId
import com.sooksook.palmtree.repository.FavoriteRepository
import com.sooksook.palmtree.repository.PlantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class FavoriteController {
    @Autowired
    lateinit var repository: FavoriteRepository

    @GetMapping("/favorites")
    fun favorites(
            @RequestParam userId: String
    ): FavoritesResult = FavoritesResult(
            repository
                    .findAllByUserId(userId)
                    .map {
                        val plant = PlantRepository.get(it.plantId)
                        FavoritesView(
                                userId = it.userId,
                                plantId = it.plantId,
                                createdAt = it.createdAt,
                                plantName = plant.name,
                                plantEngName = plant.engName,
                                imageUrl = plant.imageUrl
                        )
                    })

    @PostMapping("/favorites", consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun setFavorite(
            @RequestBody form: SetFavoriteForm
    ): JsonResult {
        repository.save(
                Favorite(
                        userId = form.userId,
                        plantId = form.plantId,
                        createdAt = LocalDateTime.now()
                )
        )
        return JsonResult()
    }

    @DeleteMapping("/favorites", consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun unsetFavorite(
            @RequestBody form: UnsetFavoriteForm
    ): JsonResult {
        val favorite = repository.findById(FavoriteId(form.userId, form.plantId))
        favorite.ifPresent { repository.delete(favorite.get()) }
        return JsonResult()
    }

    data class SetFavoriteForm(
            val userId: String,
            val plantId: PlantId
    )

    data class UnsetFavoriteForm(
            val userId: String,
            val plantId: PlantId
    )

    data class JsonResult(val status: Int = 200)

    data class FavoritesResult(
            val favorites: List<FavoritesView>,
            val status: Int = 200
    )

    data class FavoritesView(
            val userId: String,
            val plantId: PlantId,
            val createdAt: LocalDateTime,
            val plantName: String,
            val plantEngName: String,
            val imageUrl: String
    )
}