package com.example.instagramapp.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_entity")
data class User(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "fullname")
    val fullName: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "profilePicUrl")
    val profilePicUrl: String?
)
