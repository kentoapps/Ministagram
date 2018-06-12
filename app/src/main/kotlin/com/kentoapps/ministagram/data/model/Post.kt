package com.kentoapps.ministagram.data.model

import java.util.*

data class Post(
        val userId: String,
        val userName: String,
        val userImage: String,
        val Image: String,
        val likeUsers: List<User>,
        val numOfComments: Int,
        val date: Date
)