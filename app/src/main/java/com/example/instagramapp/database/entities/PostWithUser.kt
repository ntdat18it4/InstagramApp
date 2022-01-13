package com.example.instagramapp.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class PostWithUser(

    @Embedded
    val userEntity : User,

    @Relation(
        parentColumn = "id",
        entityColumn = "creator"
    )
    val postEntity: List<Post>


)
