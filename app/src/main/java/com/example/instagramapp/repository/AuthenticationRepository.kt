package com.example.instagramapp.repository

import com.example.instagramapp.database.entities.User


interface AuthenticationRepository {
    suspend fun addUser(user: User)
    suspend fun isEmailAndPasswordMatch(email: String, password: String): Boolean
}