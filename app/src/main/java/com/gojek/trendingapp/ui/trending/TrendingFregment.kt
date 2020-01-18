package com.gojek.trendingapp.ui.trending

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.gojek.trendingapp.BR
import com.gojek.trendingapp.R
import com.gojek.trendingapp.dagger.ViewModelFactory
import com.gojek.trendingapp.databinding.TrendingFragmentBinding
import com.google.android.material.snackbar.Snackbar

class TrendingFregment : Fragment() {



    companion object {
        fun newInstance() = TrendingFregment()
    }


    private lateinit var quotesBinding: TrendingFragmentBinding
    private var errorSnackbar: Snackbar? = null

    private lateinit var quotesViewModel: TrendingFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quotesViewModel = ViewModelProviders.of(this, ViewModelFactory(activity = activity!!)).get(TrendingFragmentViewModel::class.java)

        //quotesViewModel = ViewModelProviders.of(this).get(TrendingFragmentViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        quotesBinding = DataBindingUtil.inflate<TrendingFragmentBinding>(
            inflater, R.layout.trending_fragment, container, false
        )
        quotesBinding.lifecycleOwner = this


        quotesViewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })

        quotesBinding.setVariable(BR.trendingViewModel, quotesViewModel)

        return quotesBinding.root


    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(quotesBinding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.msg_retry, quotesViewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }



}
