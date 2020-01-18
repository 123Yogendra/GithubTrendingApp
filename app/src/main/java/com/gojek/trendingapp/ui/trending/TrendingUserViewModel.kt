package com.gojek.trendingapp.ui.trending

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.gojek.trendingapp.models.TrendingUser


class TrendingUserViewModel : ViewModel() {
    private val name = MutableLiveData<String>()
    private val author = MutableLiveData<String>()
    private val avatar = MutableLiveData<String>()
    private val url = MutableLiveData<String>()
    private val language = MutableLiveData<String>()
    private val star = MutableLiveData<String>()
    private val forks = MutableLiveData<String>()
    private val expended = MutableLiveData<Int>()

    fun bind(trendingUser: TrendingUser ) {
        name.value = trendingUser.name
        author.value = trendingUser.author
        avatar.value = trendingUser.avatar
        url.value = trendingUser.url
        language.value = trendingUser.language
        star.value = trendingUser.stars.toString()
        forks.value = trendingUser.forks.toString()
        expended.value = if(trendingUser.expanded) View.VISIBLE else View.GONE

    }

    fun getName(): MutableLiveData<String> {
        return name
    }

    fun getAuthor(): MutableLiveData<String> {
        return author
    }

    fun getAvatar(): MutableLiveData<String> {
        return avatar
    }

    fun getUrl(): MutableLiveData<String> {
        return url
    }

    fun getLanguage(): MutableLiveData<String> {
        return language
    }


    fun getStar(): MutableLiveData<String> {
        return star
    }

    fun getForks(): MutableLiveData<String> {
        return forks
    }

    fun getExpended(): MutableLiveData<Int> {
        return expended
    }

}