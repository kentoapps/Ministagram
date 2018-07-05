package com.kentoapps.ministagram.ui.comment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kentoapps.ministagram.databinding.CommentFragmentBinding
import com.kentoapps.ministagram.di.Injectable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CommentFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(CommentViewModel::class.java)
    }
    private lateinit var binding: CommentFragmentBinding
    private val disposables = CompositeDisposable()

    private val postId by lazy {
        arguments?.let { CommentFragmentArgs.fromBundle(it).postId }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = CommentFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = viewModel

        if (savedInstanceState == null) postId?.let { viewModel.getCommentList(it) }
    }
}
