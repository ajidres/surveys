package com.nimble.surveys.features.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nimble.surveys.R
import com.nimble.surveys.base.BaseFragment
import com.nimble.surveys.data.api.model.Result
import com.nimble.surveys.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment:BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    override fun initBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    private val bgImagesAdapter by lazy { ViewPagerImageAdapter(SurveySelectedInterface()) }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        progressBar = binding.progressBar.root
        viewModel.fetchData()
        surveyDataObserver()
        viewModel.fetchSurveysFromBD()
        actionListeners()
        binding.txtDate.text=viewModel.fetchTodayDate()
    }


    private fun surveyDataObserver() {
        viewModel.surveyData.observe(this) { result ->
            if(result.surveys.isEmpty()){
                fetchApiSurveys()
            }else{
                binding.vpImages.adapter=bgImagesAdapter
                binding.dotsIndicator.attachTo(binding.vpImages)
                bgImagesAdapter.update(result.surveys)
            }
        }
    }


    private fun actionListeners(){
        binding.swpRefresh.setOnRefreshListener {
            binding.swpRefresh.isRefreshing=false
            fetchApiSurveys()
        }
    }

    private fun fetchApiSurveys(){
        surveyDataObserverApi()
        viewModel.fetchSurveysFromApi()
    }

    private fun surveyDataObserverApi() {
        viewModel.surveyDataApi.observe(this) { result ->
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
                    Toast.makeText(requireActivity(), result.errorMessage!!.description, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    inner class SurveySelectedInterface():ViewPagerImageAdapter.SelectSurvey{
        override fun surveySelected(pos: Int) {}

    }

}