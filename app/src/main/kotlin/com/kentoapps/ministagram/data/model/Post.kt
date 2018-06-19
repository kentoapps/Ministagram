package com.kentoapps.ministagram.data.model

import java.util.*

class Post {
    var id: String? = null
    var userId: String? = null
    var userName: String? = null
    var userImage: String? = null
    var image: String? = null
    var caption: String? = null
    var likeUsers: List<User> = emptyList()
    var numOfComments: Int = 0
    var date: Date = Date()

    constructor() {}

    constructor(id: String,
                userId: String,
                userName: String,
                userImage: String,
                image: String,
                caption: String,
                likeUsers: List<User>,
                numOfComments: Int,
                date: Date) {
        this.id = id
        this.userId = userId
        this.userName = userName
        this.userImage = userImage
        this.image = image
        this.caption = caption
        this.likeUsers = likeUsers
        this.numOfComments = numOfComments
        this.date = date
    }
}