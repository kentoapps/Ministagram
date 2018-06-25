package com.kentoapps.ministagram.ui.post

import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kentoapps.ministagram.R
import kotlinx.android.synthetic.main.post_fragment.*


class PostFragment : Fragment() {
    private lateinit var viewModel: PostViewModel
    private val imageUri by lazy { Uri.parse(arguments?.let { PostFragmentArgs.fromBundle(it).imageUri }) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)

        println("========== $imageUri")
        postImage.setImageURI(imageUri)
    }
}
