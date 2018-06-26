package com.kentoapps.ministagram.ui.post

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.kentoapps.ministagram.R
import com.kentoapps.ministagram.di.Injectable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.post_fragment.*
import javax.inject.Inject

class PostFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(PostViewModel::class.java)
    }
    private val imageUri by lazy { Uri.parse(arguments?.let { PostFragmentArgs.fromBundle(it).imageUri }) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.post_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        postImage.setImageURI(imageUri)
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
        when (item?.itemId) {
            R.id.post -> print("post!!")
        }
        return super.onOptionsItemSelected(item)
    }
}
