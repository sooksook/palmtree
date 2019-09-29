package com.sooksook.palmtree.view

import com.sooksook.palmtree.model.Plant
import com.sooksook.palmtree.model.PlantId

data class PlantView(
        val plantId: PlantId,
        val name: String,
        val engName: String,
        val category: String,
        val water: String,
        val sunlight: String,
        val place: String,
        val soil: String,
        val growthType: String,
        val temparature: String,
        val difficulty: String,
        val imageUrl: String,
        val imageSource: String,
        val favorite: Boolean
) {
    companion object {
        fun of(plant: Plant, favorite: Boolean=false) = PlantView(
                plantId = plant.id,
                name = plant.name,
                engName = plant.engName,
                category = plant.category,
                water = plant.water,
                sunlight = plant.sunlight,
                place = plant.place,
                soil = plant.soil,
                growthType = plant.growthType,
                temparature = plant.temparature,
                difficulty = plant.difficulty,
                imageUrl = plant.imageUrl,
                imageSource = plant.imageSource,
                favorite = favorite
        )
    }
}