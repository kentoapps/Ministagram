package com.kentoapps.ministagram.data.model

data class User constructor(
        var userId: String? = null,
        var userName: String? = null,
        var userImage: String? = null) {
    fun toMap(): Map<String, Any> =
            HashMap<String, Any>().apply {
                userId?.let { put("userId", it) }
                userName?.let { put("userName", it) }
                userImage?.let { put("userImage", it) }
            }
}
