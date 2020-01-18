package com.datanapps.myexercise.views.motivation.text

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.gojek.trendingapp.models.TrendingUser


class TrendingUserViewModel : ViewModel() {
    private val name = MutableLiveData<String>()
    private val avatar = MutableLiveData<String>()


    fun bind(trendingUser: TrendingUser) {
        name.value = trendingUser.name
        avatar.value = trendingUser.avatar
    }

    fun getName(): MutableLiveData<String> {
        return name
    }

    fun getAvatar(): MutableLiveData<String> {
        return avatar
    }



}