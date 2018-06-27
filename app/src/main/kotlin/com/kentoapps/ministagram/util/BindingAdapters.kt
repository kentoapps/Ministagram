package com.kentoapps.ministagram.util

import android.databinding.BindingAdapter
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, url: String) {
        Picasso.get().load(url).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageUri")
    fun setImageUri(imageView: ImageView, uri: Uri) {
        imageView.setImageURI(uri)
    }
}