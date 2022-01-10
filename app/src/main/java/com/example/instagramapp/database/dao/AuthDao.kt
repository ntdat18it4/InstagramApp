package com.example.instagramapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.instagramapp.database.entities.User

@Dao
interface AuthDao {
    @Insert(entity = User::class)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    suspend fun isEmailAndPasswordMatch(email: String, password: String): List<User>
}