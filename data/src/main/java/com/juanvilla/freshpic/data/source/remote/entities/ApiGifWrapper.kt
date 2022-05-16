package com.juanvilla.freshpic.data.source.remote.entities

import com.google.gson.annotations.SerializedName

data class ApiGifWrapper(
    @SerializedName("data")
    val gifs: List<ApiGif>,
    val pagination: ApiPagination?,
    val meta: ApiMeta?
)