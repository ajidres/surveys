package com.nimble.surveys.features.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nimble.surveys.R
import com.nimble.surveys.base.BaseFragment
import com.nimble.surveys.databinding.FragmentLoginBinding
import com.nimble.surveys.extentions.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint
import com.nimble.surveys.data.api.model.Result
@AndroidEntryPoint
class LoginFragment: BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun initBinding(): FragmentLoginBinding =FragmentLoginBinding.inflate(layoutInflater)
    override fun initView(view: View, savedInstanceState: Bundle?) {
        progressBar = binding.progressBar.root
        collectUIState()
        actionListener()
        userDataObserver()
    }

    private fun collectUIState() = viewLifecycleOwner.launchAndCollect(viewModel.uiState) { state ->
        binding.edtEmail.error = if (!state.emailValid) getString(R.string.error_email) else null
        binding.edtPasssword.error = if (!state.passwordValid) getString(R.string.error_password) else null
    }

    private fun actionListener(){
        binding.btnLogin.setOnClickListener {
            viewModel.checkValues()
            viewModel.doLogin()
        }

        binding.txtForgot.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotFragment)
        }
    }

    private fun userDataObserver() {
        viewModel.userData.observe(this) { result ->
            when(result){
                is Result.Loading -> {
                    showProgress(result.show)
                }

                is Result.Success -> {
                    showProgress(false)
                    viewModel.saveData(result.data)
                }

                is Result.Failure -> {
                    showProgress(false)

                    Toast.makeText(requireActivity(), result.errorMessage!!.description,Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}