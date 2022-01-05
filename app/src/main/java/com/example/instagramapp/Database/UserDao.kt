package com.example.instagramapp.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instagramapp.Model.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserModel)

    //@Delete
    //suspend  fun deleteSubscriber(register: RegisterEntity):Int

    @Query("SELECT * FROM User_Table ORDER BY userId ASC")
    fun readAllUser() : LiveData<List<UserModel>>

    @Query("SELECT * FROM User_Table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<UserModel>>

    @Query("DELETE FROM User_Table")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM User_Table WHERE user_name LIKE :userName")
    suspend fun getUsername(userName: UserModel): UserModel?

}