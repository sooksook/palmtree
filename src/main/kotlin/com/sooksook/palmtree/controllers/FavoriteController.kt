package com.sooksook.palmtree.controllers

import com.sooksook.palmtree.model.Favorite
import com.sooksook.palmtree.model.FavoriteId
import com.sooksook.palmtree.model.PlantId
import com.sooksook.palmtree.repository.FavoriteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class FavoriteController {
    @Autowired
    lateinit var repository: FavoriteRepository

    @CrossOrigin
    @GetMapping("/favorites")
    fun favorites(
            @RequestParam userId: String
    ): FavoritesView = FavoritesView(repository.findAllByUserId(userId))

    @PostMapping("/favorites")
    fun setFavorite(
            form: SetFavoriteForm
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

    @DeleteMapping("/favorites")
    fun unsetFavorite(
            form: UnsetFavoriteForm
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

    data class FavoritesView(
            val favorites: List<Favorite>,
            val status: Int = 200
    )
}