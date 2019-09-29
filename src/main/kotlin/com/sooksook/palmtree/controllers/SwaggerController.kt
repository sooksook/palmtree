package com.sooksook.palmtree.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class SwaggerController {
    @GetMapping("/swagger")
    fun swagger(): String {
        return "redirect:/swagger-ui.html"
    }
}