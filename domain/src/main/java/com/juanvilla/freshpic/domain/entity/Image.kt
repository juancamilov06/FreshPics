package com.juanvilla.freshpic.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val height: Int,
    val width: Int,
    val url: String
) : Parcelable