package com.kentoapps.ministagram.data.model

import java.util.*

data class Comment(
        var id: String? = null,
        var userId: String? = null,
        var userName: String? = null,
        var userImage: String? = null,
        var comment: String? = null,
        var likeUsers: MutableList<User> = mutableListOf(),
        var reply: String? = null,
        var date: Date? = null) {
    constructor(user: User, comment: String) :
            this(
                    userId = user.userId,
                    userName = user.userName,
                    userImage = user.userImage,
                    comment = comment,
                    date = Date())
}