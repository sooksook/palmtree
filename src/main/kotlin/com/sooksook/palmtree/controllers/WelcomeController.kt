package com.sooksook.palmtree.controllers

import com.sooksook.palmtree.domain.Id
import com.sooksook.palmtree.domain.Plant
import com.sooksook.palmtree.domain.PlantRepository
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WelcomeController {

    @RequestMapping("/")
    fun welcome() = Welcome()

    data class Welcome(
            val status: Int = 200,
            val message: String = "Hello"
    )
}