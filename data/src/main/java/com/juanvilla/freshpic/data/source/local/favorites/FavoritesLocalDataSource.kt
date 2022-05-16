package com.juanvilla.freshpic.data.source.local.favorites

import com.juanvilla.freshpic.data.source.local.entities.DbGif
import com.juanvilla.freshpic.domain.entity.Gif
import com.juanvilla.freshpic.domain.entity.GifWrapper
import com.juanvilla.freshpic.domain.util.ResultType

interface FavoritesLocalDataSource {
    fun save(gif: DbGif): ResultType<Long>
    fun delete(gif: DbGif): ResultType<Unit>
    fun findAll(): ResultType<GifWrapper>
}