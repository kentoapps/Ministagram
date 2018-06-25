package com.kentoapps.ministagram.ui.signup

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kentoapps.ministagram.databinding.SignUpFragmentBinding
import com.kentoapps.ministagram.di.Injectable
import com.kentoapps.ministagram.ui.MainActivity
import javax.inject.Inject

class SignUpFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProviders.of(requireActivity(), viewModelFactory).get(SignUpViewModel::class.java) }
    private lateinit var binding: SignUpFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = viewModel

        viewModel.successCommand.observe(this, Observer {
            startActivity(Intent(context, MainActivity::class.java))
            requireActivity().finish()
        })
    }

}
