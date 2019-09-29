package com.sooksook.palmtree.controllers

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