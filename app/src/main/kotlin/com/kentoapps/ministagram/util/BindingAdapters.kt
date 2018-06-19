package com.kentoapps.ministagram.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("image")
    fun setImage(imageView: ImageView, url: String) {
        Picasso.get().load(url).into(imageView)
    }
}