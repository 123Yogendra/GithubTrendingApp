package com.gojek.trendingapp.dagger

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gojek.trendingapp.ui.trending.TrendingFragmentViewModel

class ViewModelFactory(private val activity: FragmentActivity): ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TrendingFragmentViewModel::class.java)) {

            return TrendingFragmentViewModel(activity) as T
        }

               throw IllegalArgumentException("Unknown ViewModel class")

    }
}