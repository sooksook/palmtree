package com.sooksook.palmtree.domain

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import org.springframework.core.io.ClassPathResource
import java.io.FileReader

data class Plant(
        val id: Id,
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
        val imageUrl: String
) {

}

data class ImageMeta(
        val id: String,
        val name: String,
        val engName: String,
        val source: String,
        val origin: String
)

typealias Id = String