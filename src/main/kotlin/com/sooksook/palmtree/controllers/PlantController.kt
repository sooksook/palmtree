package com.sooksook.palmtree.controllers

import com.sooksook.palmtree.model.FavoriteId
import com.sooksook.palmtree.model.PlantId
import com.sooksook.palmtree.repository.FavoriteRepository
import com.sooksook.palmtree.repository.PlantRepository
import com.sooksook.palmtree.view.PlantView
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PlantController {
    @Autowired
    lateinit var favoriteRepository: FavoriteRepository

    @GetMapping("/plants")
    @ApiOperation("""
        식물 정보 리스트를 내려준다.
        파라미터에 userId가 있으면 favorite 정보를 확인해서 내려주며 favorite true 순으로 내려준다.
        userId가 없으면 favorite은 모두 false.
        """
    )
    fun plants(@RequestParam(required = false) userId: String?): PlantsResponse {
        val plants = PlantRepository().getAll()
        val favorites = userId?.let {
            favoriteRepository.findAllByUserId(userId).map {
                it.plantId to it
            }.toMap()
        } ?: emptyMap()

        return PlantsResponse(
                plants
                        .map {
                            PlantView.of(
                                    plant = it,
                                    favorite = favorites[it.id]?.let { true } ?: false)
                        }
                        .sortedByDescending { it.favorite }
        )
    }

    @GetMapping("/plants/{plantId}")
    @ApiImplicitParams(
            ApiImplicitParam(name = "userId", value = "있으면 favorite 여부 확인한다.", required = false, paramType = "query")
    )
    fun plant(
            @PathVariable plantId: PlantId,
            @RequestParam(required = false) userId: String?
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