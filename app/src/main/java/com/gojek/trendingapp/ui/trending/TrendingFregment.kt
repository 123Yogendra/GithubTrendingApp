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
import com.gojek.trendingapp.databinding.TrendingFragmentBinding
import com.google.android.material.snackbar.Snackbar


class TrendingFregment : Fragment() {



    companion object {
        fun newInstance() = TrendingFregment()
    }


    private lateinit var trendingFragmentBinding: TrendingFragmentBinding
    private var errorSnackbar: Snackbar? = null

    private lateinit var trendingFragmentViewModel: TrendingFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trendingFragmentViewModel = ViewModelProviders.of(this, ViewModelFactory(activity = activity!!)).get(TrendingFragmentViewModel::class.java)

        //trendingFragmentViewModel = ViewModelProviders.of(this).get(TrendingFragmentViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        trendingFragmentBinding = DataBindingUtil.inflate<TrendingFragmentBinding>(
            inflater, R.layout.trending_fragment, container, false
        )
        trendingFragmentBinding.lifecycleOwner = this





        trendingFragmentViewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

        trendingFragmentBinding.setVariable(BR.trendingViewModel, trendingFragmentViewModel)

        return trendingFragmentBinding.root


    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(trendingFragmentBinding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.msg_retry, trendingFragmentViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }



}
