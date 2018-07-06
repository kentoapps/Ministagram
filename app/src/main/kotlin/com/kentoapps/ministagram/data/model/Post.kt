package com.kentoapps.ministagram.data.model

import java.util.*

data class Post constructor(
        var id: String? = "test",
        var userId: String? = null,
        var userName: String? = null,
        var userImage: String? = null,
        var image: String? = null,
        var caption: String? = null,
        var likeUsers: MutableList<User> = mutableListOf(),
        var isLike: Boolean = false,
        var numOfComments: Int = 0,
        var date: Date = Date()) {

    constructor(pr: PostRequest, imageUrl: String) :
            this(
                    userId = pr.userId,
                    userName = pr.userName,
                    userImage = pr.userImage,
                    image = imageUrl,
                    caption = pr.caption,
                    date = Date())
}
