package com.juanvilla.freshpic.domain.entity

data class User(
    val avatarUrl: String,
    val bannerUrl: String,
    val profileUrl: String,
    val username: String,
    val displayName: String
)