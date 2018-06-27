package com.kentoapps.ministagram.ui.post

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.kentoapps.ministagram.R
import com.kentoapps.ministagram.databinding.PostFragmentBinding
import com.kentoapps.ministagram.di.Injectable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class PostFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(PostViewModel::class.java)
    }
    private val imageUri by lazy {
        Uri.parse(arguments?.let { PostFragmentArgs.fromBundle(it).imageUri })
    }
    private lateinit var binding: PostFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = PostFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = viewModel
        viewModel.uri.set(imageUri)
    }

    override fun onResume() {
        super.onResume()
        requireActivity().bottom_nav_view.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        requireActivity().bottom_nav_view.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_post, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        println("======== onOptionsItemSelected")
        when (item?.itemId) {
            R.id.post -> {
                println("======== post")
                viewModel.savePost()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
