package com.nimble.surveys.features.init

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.nimble.surveys.R
import com.nimble.surveys.base.BaseActivity
import com.nimble.surveys.databinding.ActivityMainBinding
import com.nimble.surveys.features.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    override fun initBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun initView(savedInstanceState: Bundle?) {
        fullScreen()
        checkLogin()
    }

    private fun checkLogin(){
        userDataObserver()
        viewModel.fetchData()
    }

    private fun userDataObserver() {
        viewModel.userData.observe(this) { result ->
            if(result.accessToken.isNotEmpty()){
                Navigation.findNavController(binding.navHostFragment).navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }
    }

}
