package com.gojek.trendingapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BuiltBy(
    @SerializedName("username")
    @Expose
    private var username: String,

    @SerializedName("href")
    @Expose
    private var href: String,

    @SerializedName("avatar")
    @Expose
    private var avatar: String

)