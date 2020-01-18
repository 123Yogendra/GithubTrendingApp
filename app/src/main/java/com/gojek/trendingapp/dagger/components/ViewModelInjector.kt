package com.gojek.trendingapp.dagger.components

import com.gojek.trendingapp.dagger.modules.NetworkModule
import com.gojek.trendingapp.ui.trending.TrendingFragmentViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {


    /**
     * Injects required dependencies into the specified TrendingFragmentViewModel.
     * @param trendingFragmentViewModel TrendingFragmentViewModel in which to inject the dependencies
     */
    fun inject(trendingFragmentViewModel: TrendingFragmentViewModel)

    @Component.Builder
    interface Builder {

        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder

    }
}