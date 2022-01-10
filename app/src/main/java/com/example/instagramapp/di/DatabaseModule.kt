package com.example.instagramapp.di

import androidx.preference.PreferenceManager
import androidx.room.Room
import com.example.instagramapp.database.InstagramAppDatabase
import com.example.instagramapp.repository.AuthenticationRepository
import com.example.instagramapp.repository.AuthenticationRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single { Room.databaseBuilder(androidContext(), InstagramAppDatabase::class.java, "ins_db.db").build() }
    single { get<InstagramAppDatabase>().dao }
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }
}