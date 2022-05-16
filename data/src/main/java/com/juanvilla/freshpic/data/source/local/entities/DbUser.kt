package com.juanvilla.freshpic.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class DbUser(
    @PrimaryKey
    val username: String,
    val avatarUrl: String,
    val bannerUrl: String,
    val profileUrl: String,
    val displayName: String
)