package com.gojek.trendingapp.ui.trending

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.gojek.trendingapp.R
import com.gojek.trendingapp.models.TrendingUser
import com.gojek.trendingapp.network.TrendingService
import com.gojek.trendingapp.base.BaseViewModel
import com.gojek.trendingapp.dagger.modules.NetworkModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class TrendingFragmentViewModel(context: Context) : BaseViewModel(context) {


    @Inject
    lateinit var trendingService: TrendingService

    var isLoading = ObservableBoolean()
    val trendingUserListAdapter: TrendingUserListAdapter =
        TrendingUserListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()


    // handle error detail

    val errorMessage = MutableLiveData<String>()

    val errorLayout = MutableLiveData<Int>().apply {
        value = View.GONE
    }

    private lateinit var subscription: Disposable

    init {
        loadTrendingData()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    /* Needs to be public for Databinding */
    fun onPulltoRefresh() {
        isLoading.set(true)
        loadTrendingData()
    }

    fun loadTrendingData() {


            subscription = trendingService.getTrendingUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePostListStart() }
                .doOnTerminate { onRetrievePostListFinish() }
                .subscribe(
                    { baseQuotes -> onRetrieveSuccess(baseQuotes) },
                    { e -> onRetrieveError(e) }
                )


    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
        isLoading.set(false)
    }

    private fun onRetrieveSuccess(trendingUserList: List<TrendingUser>) {
        isLoading.set(false)
        errorLayout.value = View.GONE
        trendingUserListAdapter.updatePostList(trendingUserList)
    }

    private fun onRetrieveError(throwable: Throwable) {
        isLoading.set(false)
        errorLayout.value = View.VISIBLE
        errorMessage.value = throwable.message

    }
}
