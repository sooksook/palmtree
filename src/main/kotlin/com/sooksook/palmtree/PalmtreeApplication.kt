package com.sooksook.palmtree

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PalmtreeApplication

fun main(args: Array<String>) {
    runApplication<PalmtreeApplication>(*args) {
        setBannerMode(Banner.Mode.CONSOLE)
    }
}
