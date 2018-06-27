package com.kentoapps.ministagram.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import androidx.navigation.fragment.NavHostFragment
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

        viewModel.successCommand.observe(this, Observer {
            NavHostFragment.findNavController(this).popBackStack()
        })
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        requireActivity().bottomNavView.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        requireActivity().bottomNavView.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_post, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.post -> viewModel.savePost()
            android.R.id.home -> NavHostFragment.findNavController(this).popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}
