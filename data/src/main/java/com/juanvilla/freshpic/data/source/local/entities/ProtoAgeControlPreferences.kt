package com.juanvilla.freshpic.data.source.local.entities

import kotlinx.serialization.Serializable

@Serializable
data class ProtoAgeControlPreferences(
    val isAdult: Boolean? = null,
    val selectedRating: String? = null
)