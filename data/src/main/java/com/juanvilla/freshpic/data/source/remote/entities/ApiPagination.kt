package com.juanvilla.freshpic.data.source.remote.entities

import com.google.gson.annotations.SerializedName

data class ApiPagination(
    val offset: Int,
    @SerializedName("total_count")
    val totalCount: Int?,
    val count: Int
)