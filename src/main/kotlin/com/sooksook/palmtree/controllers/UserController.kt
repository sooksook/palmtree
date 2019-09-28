package com.sooksook.palmtree.controllers

import com.sooksook.palmtree.domain.Id
import com.sooksook.palmtree.domain.Plant
import com.sooksook.palmtree.domain.PlantRepository
import com.sooksook.palmtree.model.User
import com.sooksook.palmtree.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @Autowired
    lateinit var repository: UserRepository

    @RequestMapping("/users")
    fun users() = UsersView(repository.findAll())

    data class UsersView(
            val users: List<User>,
            val status: Int = 200
    )
}