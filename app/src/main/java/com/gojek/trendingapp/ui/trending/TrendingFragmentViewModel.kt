package com.gojek.trendingapp.ui.trending

import android.content.Context
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.gojek.trendingapp.R
import com.gojek.trendingapp.base.BaseViewModel
import com.gojek.trendingapp.models.TrendingUser
import com.gojek.trendingapp.network.TrendingService
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

    /* binding for pull to refresh */
    fun onPulltoRefresh() {
        isLoading.set(true)
        loadTrendingData()
    }

    fun loadTrendingData() {
        errorLayout.value = View.GONE
            subscription = trendingService.getTrendingUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveStart() }
                .doOnTerminate { onRetrieveFinish() }
                .subscribe(
                    { baseQuotes -> onRetrieveSuccess(baseQuotes) },
                    { e -> onRetrieveError(e) }
                )

    }

    private fun onRetrieveStart() {
        loadingVisibility.value = View.VISIBLE

    }

    private fun onRetrieveFinish() {
        loadingVisibility.value = View.GONE
        isLoading.set(false)
    }

    private fun onRetrieveSuccess(trendingUserList: List<TrendingUser>) {
        isLoading.set(false)
        errorLayout.value = View.GONE
        trendingUserListAdapter.updateTrendingUserList(trendingUserList)
    }

    private fun onRetrieveError(throwable: Throwable) {

        isLoading.set(false)
        errorLayout.value = View.VISIBLE
        errorMessage.value = context.getString(R.string.msg_fail_to_load) //throwable.message

    }
}
