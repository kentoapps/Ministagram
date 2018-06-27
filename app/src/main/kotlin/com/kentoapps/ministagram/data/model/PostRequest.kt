package com.kentoapps.ministagram.data.model

import android.net.Uri

data class PostRequest(
        val uri: Uri,
        val caption: String,
        val userId: String,
        val userName: String,
        val userImage: String)