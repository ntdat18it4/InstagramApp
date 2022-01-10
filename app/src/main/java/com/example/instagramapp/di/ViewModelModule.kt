package com.example.instagramapp.di

import com.example.instagramapp.viewmodels.SignInViewModel
import com.example.instagramapp.viewmodels.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { SignInViewModel(get(), get()) }
}