package com.juanvilla.freshpic.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.juanvilla.freshpic.data.source.local.dao.FavoritesDao
import com.juanvilla.freshpic.data.source.local.entities.DbGif

@Database(entities = [DbGif::class], version = 1, exportSchema = false)
abstract class GifDatabase : RoomDatabase() {

    abstract fun getFavoritesDao(): FavoritesDao
}