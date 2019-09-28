package com.sooksook.palmtree

import org.springframework.context.annotation.Configuration
import org.springframework.http.CacheControl
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class StaticResourceConfig: WebMvcConfigurerAdapter() {
    val RESOURCE_LOCACTION = "classpath:/"

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations(RESOURCE_LOCACTION + "static/")

        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/WEB-INF/resources/")

        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
}