package com.sooksook.palmtree.controllers

import com.sooksook.palmtree.model.User
import com.sooksook.palmtree.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class UserController {
    @Autowired
    lateinit var repository: UserRepository

    @GetMapping("/users")
    fun users() = ResponseEntity.ok().header("Access-Control-Allow-Origin", "*").body(UsersView(repository.findAll()))

    @PostMapping("/users", consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun signup(@RequestBody form: SignupForm) {
        val headers = HttpHeaders()
        repository.save(
                User(
                        id = form.loginBy.prefix + form.id,
                        nickname = form.nickname,
                        createdAt = LocalDateTime.now()
                )
        )
    }

    data class SignupForm(
        val id: String,
        val nickname: String,
        val loginBy: LoginService
    )

    data class UsersView(
            val users: List<User>,
            val status: Int = 200
    )

    enum class LoginService(
            val prefix: String
    ) {
        KAKAO("ka"), GOOGLE("gg")
    }
}