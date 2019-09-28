package com.sooksook.palmtree.model

import java.time.LocalDateTime


class User(
        val id: String,
        val nickname: String,
        val createdAt: LocalDateTime
)