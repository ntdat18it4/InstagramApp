package com.example.instagramapp.viewmodels

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramapp.database.entities.User
import com.example.instagramapp.repository.AuthenticationRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _registerResult = MutableSharedFlow<Boolean>(replay = 0)
    val registerResult = _registerResult.asSharedFlow()

    fun register(user: User) = viewModelScope.launch {
        authenticationRepository.addUser(user)
        sharedPreferences.edit {
            putBoolean("isLoggedIn", true)
//            putInt("userId", userDb.id)
        }
        _registerResult.emit(true)
    }
}