package com.kentoapps.ministagram.ui.account

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.kentoapps.ministagram.R
import com.kentoapps.ministagram.databinding.SignUpFragmentBinding
import com.kentoapps.ministagram.di.Injectable
import javax.inject.Inject

class SignUpFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProviders.of(requireActivity(), viewModelFactory).get(AccountViewModel::class.java) }
    private lateinit var binding: SignUpFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = viewModel

        viewModel.errorMessage.set("")
        viewModel.successCommand.observe(this, Observer {
            NavHostFragment.findNavController(this).navigate(R.id.main_activity)
            requireActivity().finish()
        })
    }
}
