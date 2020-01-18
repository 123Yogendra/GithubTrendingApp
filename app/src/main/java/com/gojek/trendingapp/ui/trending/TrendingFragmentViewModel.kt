package com.gojek.trendingapp.ui.trending

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.gojek.trendingapp.models.TrendingUserList
import com.gojek.trendingapp.network.TrendingService
import com.gojek.trendingapp.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrendingFragmentViewModel : BaseViewModel() {


    @Inject
    lateinit var trendingService: TrendingService


   /* val quotesListAdapter: QuotesListAdapter =
        QuotesListAdapter()*/

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadPosts() {

        subscription = trendingService.getTrendingUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { baseQuotes -> onRetrievePostListSuccess(baseQuotes) },
                { onRetrievePostListError() }
            )

    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(trendingUserList: TrendingUserList) {
        Log.d("asd", "test......... success")
        //quotesListAdapter.updatePostList(baseQuotes.motivationtextlist)
    }

    private fun onRetrievePostListError() {
        //errorMessage.value = R.string.load_data_error
    }
}
