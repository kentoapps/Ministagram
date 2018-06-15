package com.kentoapps.ministagram.data.model

import java.util.*

data class Post(
        val id: String,
        val userId: String,
        val userName: String,
        val userImage: String,
        val image: String,
        val caption: String,
        val likeUsers: List<User>,
        val numOfComments: Int,
        val date: Date
)