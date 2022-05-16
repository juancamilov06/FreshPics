package com.juanvilla.freshpic.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gif")
data class DbGif(
    @PrimaryKey
    val id: String,
    val type: String,
    val slug: String,
    val url: String,
    val bitlyUrl: String,
    val embedUrl: String,
    val username: String,
    val source: String,
    val rating: String,
    val updateDateTime: String,
    val createDateTime: String,
    val importDateTime: String,
    val trendingDateTime: String?,
    val title: String
)