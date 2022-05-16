package com.juanvilla.freshpic.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.juanvilla.freshpic.data.source.local.entities.DbGif
import com.juanvilla.freshpic.data.source.local.entities.DbUser

@Dao
interface FavoritesDao {
    /*@Query(
        "SELECT * FROM gif as g " +
        "JOIN user on user.username = g.username"
    )
    suspend fun findAll(): Map<DbGif, DbUser>*/

    @Query("SELECT * FROM gif")
    fun findAll(): LiveData<List<DbGif>>

    @Delete
    fun delete(gif: DbGif): Int

    @Insert
    fun save(gif: DbGif): Long
}