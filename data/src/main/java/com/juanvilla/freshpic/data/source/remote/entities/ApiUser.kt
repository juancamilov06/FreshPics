package com.juanvilla.freshpic.data.source.remote.entities

import com.google.gson.annotations.SerializedName

data class ApiUser(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("banner_url")
    val bannerUrl: String,
    @SerializedName("profile_url")
    val profileUrl: String,
    val username: String,
    @SerializedName("display_name")
    val displayName: String
)