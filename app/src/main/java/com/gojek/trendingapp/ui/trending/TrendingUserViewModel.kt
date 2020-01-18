package com.datanapps.myexercise.views.motivation.text

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.gojek.trendingapp.models.TrendingUser


class TrendingUserViewModel : ViewModel() {
    private val name = MutableLiveData<String>()
    private val author = MutableLiveData<String>()
    private val avatar = MutableLiveData<String>()


    fun bind(trendingUser: TrendingUser) {
        name.value = trendingUser.name
        author.value = trendingUser.author
        avatar.value = trendingUser.avatar
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



}