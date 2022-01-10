package com.example.instagramapp.repository

import com.example.instagramapp.database.dao.AuthDao
import com.example.instagramapp.database.entities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthenticationRepositoryImpl(private val authDao: AuthDao) : AuthenticationRepository {
    override suspend fun addUser(user: User) = withContext(Dispatchers.IO) {
        authDao.addUser(user)
    }

    override suspend fun isEmailAndPasswordMatch(email: String, password: String) =
        withContext(Dispatchers.IO) {
            authDao.isEmailAndPasswordMatch(email, password).isNotEmpty()
        }
}