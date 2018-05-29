package com.kentoapps.ministagram.data.model

import java.util.*

data class Comment(
        val userId: String,
        val userName: String,
        val userImage: String,
        val comment: String,
        val likeUsers: List<User>,
        val reply: String,
        val date: Date
)