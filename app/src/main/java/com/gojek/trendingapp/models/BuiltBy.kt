package com.gojek.trendingapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BuiltBy(
    @SerializedName("username")
    @Expose
     var username: String,

    @SerializedName("href")
    @Expose
     var href: String,

    @SerializedName("avatar")
    @Expose
     var avatar: String

)