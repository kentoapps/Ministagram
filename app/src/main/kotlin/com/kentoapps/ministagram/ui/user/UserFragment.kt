package com.kentoapps.ministagram.ui.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kentoapps.ministagram.databinding.UserFragmentBinding
import com.kentoapps.ministagram.di.Injectable
import com.kentoapps.ministagram.ui.AccountActivity
import javax.inject.Inject

class UserFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(requireActivity(), viewModelFactory).get(UserViewModel::class.java)
    }
    private lateinit var binding: UserFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = UserFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = viewModel

        viewModel.successCommand.observe(this, Observer {
            startActivity(Intent(context, AccountActivity::class.java))
            requireActivity().finish()
        })
    }

}
