package com.sooksook.palmtree.controllers

import com.sooksook.palmtree.model.FavoriteId
import com.sooksook.palmtree.model.PlantId
import com.sooksook.palmtree.model.Plant
import com.sooksook.palmtree.repository.FavoriteRepository
import com.sooksook.palmtree.repository.PlantRepository
import com.sooksook.palmtree.view.PlantView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PlantController {
    @Autowired
    lateinit var favoriteRepository: FavoriteRepository

    @GetMapping("/plants")
    fun plants(@RequestParam userId: String?): PlantsResponse {
        val plants = PlantRepository().getAll()
        val favorites = userId?.let {
            favoriteRepository.findAllByUserId(userId).map {
                it.plantId to it
            }.toMap()
        } ?: emptyMap()

        return PlantsResponse(
                plants.map { PlantView.of(it, favorites[it.id]?.let { true } ?: false) }
        )
    }

    @GetMapping("/plants/{plantId}")
    fun plant(
            @PathVariable plantId: PlantId,
            @RequestParam userId: String?
    ): PlantResponse {
        return PlantResponse(
                PlantView.of(
                        plant = PlantRepository().get(plantId),
                        favorite = userId?.let {
                            favoriteRepository.existsById(FavoriteId(userId, plantId))
                        } ?: false
                )
        )
    }

    data class PlantsResponse(
            val plants: List<PlantView>,
            val status: Int = 200
    )

    data class PlantResponse(
            val plant: PlantView,
            val status: Int = 200
    )
}