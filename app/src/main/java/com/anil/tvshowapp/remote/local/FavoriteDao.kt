package com.anil.tvshowapp.remote.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anil.tvshowapp.domain.Show

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun getAllShows(): List<Show>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(show: Show)

    @Delete
    fun deleteUser(show: Show)
}