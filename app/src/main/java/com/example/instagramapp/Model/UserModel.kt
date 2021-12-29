package com.example.instagramapp.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "User_Table")
data class UserModel(

    @ColumnInfo(name = "UserId")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "Full_Name")
    val fullName: String?,

    @ColumnInfo(name = "User_Name")
    val userName: String?,

    @ColumnInfo(name = "Email")
    val email: String,

    @ColumnInfo(name = "Password")
    val password: String
)