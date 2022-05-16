package com.juanvilla.freshpic.data.source.remote.entities

import com.google.gson.annotations.SerializedName

data class ApiGif(
    val type: String,
    val id: String,
    val slug: String,
    val url: String,
    @SerializedName("bitly_url")
    val bitlyUrl: String,
    @SerializedName("embed_url")
    val embedUrl: String,
    val username: String,
    val source: String,
    val rating: String,
    @SerializedName("update_datetime")
    val updateDateTime: String?,
    @SerializedName("create_datetime")
    val createDateTime: String?,
    @SerializedName("import_datetime")
    val importDateTime: String?,
    @SerializedName("trending_datetime")
    val trendingDateTime: String?,
    val title: String,
    val isFavorite: Boolean = false,
    val user: ApiUser?,
    val images: ApiImages?
)