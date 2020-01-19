package com.gojek.trendingapp.dagger.modules

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.gojek.trendingapp.network.TrendingService
import com.gojek.trendingapp.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Response

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module

@Suppress("unused")
class NetworkModule (var context: Context) {


    val isConnected : Boolean    get(){
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            capabilities != null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        } else {
            connectivityManager.activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
        }
    }

    @Provides
    @Reusable
    internal fun provideMotivationalQuotesAPI(retrofit: Retrofit): TrendingService {
        return retrofit.create(TrendingService::class.java)
    }




    @Provides
    @Reusable
    internal fun provideHttpCache(): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        // for caching

       return OkHttpClient.Builder()
            .cache(myCache)
            // add off line interceptor
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (isConnected)
                    // cache 2 hours only
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 60 * 60 * 2).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 2
                    ).build()
                chain.proceed(request)
            }
            // add on line interceptor
            .addNetworkInterceptor {

                val response: Response = it.proceed(it.request())
                val maxAge =
                    60 // read from cache for 60 seconds even if there is internet connection

                response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .removeHeader("Pragma")
                    .build()
            }
            .build()
    }





    @Provides
    @Reusable

    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            //.client(provideHttpCache())
            .build()
    }
}