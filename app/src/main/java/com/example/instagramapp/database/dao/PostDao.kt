package com.example.instagramapp.database.dao

import androidx.room.*
import com.example.instagramapp.database.entities.Post
import com.example.instagramapp.database.entities.User

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Post)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entity: List<Post>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPostCreator(entity: User)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPostCreatorAll(entity: List<User>)


    @Delete
    fun delete(entity: Post)

}