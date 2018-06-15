package com.kentoapps.ministagram.ui.timeline

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kentoapps.ministagram.databinding.TimelineFragmentBinding
import com.kentoapps.ministagram.di.Injectable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TimelineFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(activity!!, viewModelFactory).get(TimelineViewModel::class.java)
    }
    private lateinit var binding: TimelineFragmentBinding
    private val disposables = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = TimelineFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = TimelineAdapter(viewModel)
        viewModel.posts.subscribe { adapter.submitList(it) }.addTo(disposables)
        binding.timelineRecycler.adapter = adapter
    }

    override fun onDestroyView() {
        disposables.clear()
        super.onDestroyView()
    }
}
