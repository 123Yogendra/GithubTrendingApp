package com.gojek.trendingapp.base

import android.content.Context
import androidx.lifecycle.ViewModel
import com.gojek.trendingapp.dagger.components.DaggerViewModelInjector
import com.gojek.trendingapp.dagger.components.ViewModelInjector

import com.gojek.trendingapp.dagger.modules.NetworkModule
import com.gojek.trendingapp.ui.trending.TrendingFragmentViewModel

abstract class BaseViewModel(var context: Context) : ViewModel() {


    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule(context))
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is TrendingFragmentViewModel -> injector.inject(this)
        }
    }
}