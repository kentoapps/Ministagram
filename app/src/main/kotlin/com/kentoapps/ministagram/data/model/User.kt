package com.kentoapps.ministagram.data.model

class User {
    var userId: String? = null
    var userName: String? = null
    var userImage: String? = null

    constructor()

    constructor(userId: String,
                userName: String,
                userImage: String) {
        this.userId = userId
        this.userName = userName
        this.userImage = userImage
    }
}