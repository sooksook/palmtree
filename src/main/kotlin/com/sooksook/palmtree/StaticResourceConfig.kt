package com.sooksook.palmtree

import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class StaticResourceConfig: WebMvcConfigurerAdapter() {
    val RESOURCE_LOCACTION = "classpath:/"

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        val resourceHandlerRegistration = registry.addResourceHandler("/static/**")
        resourceHandlerRegistration.addResourceLocations(RESOURCE_LOCACTION + "static/")
    }
}