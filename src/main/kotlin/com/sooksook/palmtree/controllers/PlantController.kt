package com.sooksook.palmtree.controllers

import com.sooksook.palmtree.domain.Id
import com.sooksook.palmtree.domain.Plant
import com.sooksook.palmtree.domain.PlantRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PlantController {
    @GetMapping("/plants")
    fun plants() = PlantsResponse(PlantRepository().getAll())

    @GetMapping("/plants/{id}")
    fun plant(@PathVariable id: Id) = PlantResponse(PlantRepository().get(id))

    data class PlantsResponse(
            val plants: List<Plant>,
            val status: Int = 200
    )

    data class PlantResponse(
            val plant: Plant,
            val status: Int = 200
    )
}