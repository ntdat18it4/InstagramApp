package com.example.instagramapp.viewmodels

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramapp.repository.AuthenticationRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _loginStatus = MutableStateFlow<Boolean>(false)
    val loginStatus = _loginStatus.asStateFlow()

    init {
        viewModelScope.launch {
            _loginStatus.update {
                sharedPreferences.getBoolean(
                    "isLoggedIn",
                    false
                )
            }
        }
    }

    fun signIn(email: String, password: String) = viewModelScope.launch {
        authenticationRepository.isEmailAndPasswordMatch(email, password)
    }
}