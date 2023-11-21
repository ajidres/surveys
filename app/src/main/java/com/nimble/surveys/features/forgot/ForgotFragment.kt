package com.nimble.surveys.features.forgot

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nimble.surveys.R
import com.nimble.surveys.base.BaseFragment
import com.nimble.surveys.databinding.FragmentForgotBinding
import com.nimble.surveys.features.login.LoginViewModel

class ForgotFragment: BaseFragment<FragmentForgotBinding>() {

    private val viewModel: ForgotViewModel by viewModels()

    override fun initBinding(): FragmentForgotBinding = FragmentForgotBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        actionListeners()
    }

    private fun actionListeners(){

        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_forgotFragment_to_loginFragment)
        }

        binding.btnReset.setOnClickListener {
            viewModel.checkValues()
            viewModel.doReset()
        }
    }
}