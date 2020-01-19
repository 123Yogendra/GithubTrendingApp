package com.gojek.trendingapp.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gojek.trendingapp.BR
import com.gojek.trendingapp.R
import com.gojek.trendingapp.dagger.ViewModelFactory
import com.gojek.trendingapp.databinding.FragmentTrendingBinding

import com.google.android.material.snackbar.Snackbar


class TrendingFregment : Fragment() {

    companion object {
        fun newInstance() = TrendingFregment()
    }


    private lateinit var trendingFragmentBinding: FragmentTrendingBinding
    private lateinit var trendingFragmentViewModel: TrendingFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trendingFragmentViewModel =
            ViewModelProviders.of(this, ViewModelFactory(activity = activity!!))
                .get(TrendingFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        trendingFragmentBinding = DataBindingUtil.inflate<FragmentTrendingBinding>(
            inflater, R.layout.fragment_trending, container, false
        )
        trendingFragmentBinding.lifecycleOwner = this
        trendingFragmentBinding.setVariable(BR.trendingViewModel, trendingFragmentViewModel)

        return trendingFragmentBinding.root
    }


}
