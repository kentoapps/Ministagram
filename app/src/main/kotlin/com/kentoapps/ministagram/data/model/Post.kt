package com.kentoapps.ministagram.data.model

import java.util.*

data class Post constructor(
        var id: String? = null,
        var userId: String? = null,
        var userName: String? = null,
        var userImage: String? = null,
        var image: String? = null,
        var caption: String? = null,
        var likeUsers: List<User> = emptyList(),
        var numOfComments: Int = 0,
        var date: Date = Date())
