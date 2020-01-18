package com.gojek.trendingapp.ui.trending

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.datanapps.myexercise.views.motivation.text.TrendingUserListAdapter
import com.gojek.trendingapp.models.TrendingUser
import com.gojek.trendingapp.network.TrendingService
import com.gojek.trendingapp.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrendingFragmentViewModel (context: Context): BaseViewModel(context) {


    @Inject
    lateinit var trendingService: TrendingService


   val trendingUserListAdapter: TrendingUserListAdapter =
        TrendingUserListAdapter()

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
                { e-> onRetrievePostListError(e) }
            )

    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(trendingUserList: List<TrendingUser>) {
        Log.d("asd", "test......... success :: "+trendingUserList)
        trendingUserListAdapter.updatePostList(trendingUserList)
    }

    private fun onRetrievePostListError(throwable: Throwable) {
        Log.d("asd", "test......... error  :: "+throwable.message)
        //errorMessage.value = R.string.load_data_error
    }
}
