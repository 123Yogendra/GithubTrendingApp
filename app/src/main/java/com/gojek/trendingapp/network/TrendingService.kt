package com.gojek.trendingapp.network

import com.gojek.trendingapp.models.TrendingUser
import io.reactivex.Observable
import retrofit2.http.GET

interface TrendingService {

    //https://github-trending-api.now.sh/repositories?language=&since=daily&spoken_language_code=

    @GET("repositories?language=&since=daily&spoken_language_code=")
    fun getTrendingUserList(): Observable<List<TrendingUser>>


}