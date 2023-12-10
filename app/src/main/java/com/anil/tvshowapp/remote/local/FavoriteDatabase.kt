package com.anil.tvshowapp.remote.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anil.tvshowapp.domain.Show

@Database(entities = [Show::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase(){
    abstract fun favoriteDao(): FavoriteDao
}