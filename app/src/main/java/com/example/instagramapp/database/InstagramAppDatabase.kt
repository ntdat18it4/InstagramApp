package com.example.instagramapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.instagramapp.database.dao.AuthDao
import com.example.instagramapp.database.entities.User

@Database(entities = [User::class], version = 1)
abstract class InstagramAppDatabase : RoomDatabase() {
    abstract val dao: AuthDao
}