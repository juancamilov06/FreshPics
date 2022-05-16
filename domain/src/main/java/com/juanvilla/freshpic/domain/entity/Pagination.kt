package com.juanvilla.freshpic.domain.entity

data class Pagination(
    val offset: Int,
    val totalCount: Int?,
    val count: Int
)