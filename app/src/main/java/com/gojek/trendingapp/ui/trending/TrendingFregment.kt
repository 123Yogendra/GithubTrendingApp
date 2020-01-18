package com.gojek.trendingapp.ui.trending

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gojek.trendingapp.R

class TrendingFregment : Fragment() {

    companion object {
        fun newInstance() = TrendingFregment()
    }

    private lateinit var viewModel: TrendingFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.trending_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrendingFragmentViewModel::class.java)

    }

}
