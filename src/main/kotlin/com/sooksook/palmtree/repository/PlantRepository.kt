package com.sooksook.palmtree.repository

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.opencsv.CSVParserBuilder
import com.opencsv.CSVReaderBuilder
import com.sooksook.palmtree.model.Id
import com.sooksook.palmtree.model.ImageMeta
import com.sooksook.palmtree.model.Plant
import org.springframework.core.io.ClassPathResource
import java.io.InputStreamReader

class PlantRepository(
        private val repository: Map<Id, Plant> = initRepository()
) {
    companion object {
        private lateinit var repository: Map<Id, Plant>

        private fun initRepository(): Map<Id, Plant> {
            val dbResource = ClassPathResource("static/db/plant_database.tsv")
            val plantCsvDataReader = CSVReaderBuilder(InputStreamReader(dbResource.inputStream))
                    .withCSVParser(
                            CSVParserBuilder()
                                    .withSeparator('\t')
                                    .build()
                    )
                    .build()
            plantCsvDataReader.readNext()

            val imageMeta = jacksonObjectMapper()
                    .readValue<List<ImageMeta>>(
                            InputStreamReader(ClassPathResource("static/img/image-data.json").inputStream)
                    )
                    .map { it.name to it }
                    .toMap()

            return plantCsvDataReader.readAll()
                    .filter {
                        imageMeta.containsKey(it[0])
                    }.map {
                        imageMeta.getValue(it[0]).id to
                                Plant(
                                        imageMeta.getValue(it[0]).id,
                                        it[0],
                                        it[1],
                                        it[2],
                                        it[3],
                                        it[4],
                                        it[5],
                                        it[6],
                                        it[7],
                                        it[8],
                                        it[9],
                                        "/static/img/${imageMeta.getValue(it[0]).id}.jpg",
                                        imageMeta.getValue(it[0]).source
                                )
                    }
                    .toMap()
                    .apply {
                        repository = this
                    }
        }

        private fun repository() = repository
                ?: initRepository()

        fun get(id: Id): Plant = repository().getValue(id)

        fun getAll(): List<Plant> = repository().values.toList()
    }

    fun get(id: Id): Plant = repository.getValue(id)

    fun getAll(): List<Plant> = repository.values.toList()
}