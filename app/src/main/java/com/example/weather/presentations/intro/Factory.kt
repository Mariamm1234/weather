package com.example.weather.presentations.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.domain.reposatories.apiRepo

//class Factory(private val repo: apiRepo): ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(introViewModel::class.java)){
//            return introViewModel(repo) as T
//        }
//
//    }
//}