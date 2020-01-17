package com.gojek.trendingapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TrendingUserList(

    @SerializedName("author")
    @Expose
    private var author: String,

    @SerializedName("name")
    @Expose
    private var name: String,

    @SerializedName("avatar")
    @Expose
    private var avatar: String,

    @SerializedName("url")
    @Expose
    private var url: String,

    @SerializedName("description")
    @Expose
    private var description: String,

    @SerializedName("language")
    @Expose
    private var language: String,

    @SerializedName("languageColor")
    @Expose
    private var languageColor: String,

    @SerializedName("stars")
    @Expose
    private var stars: Int,

    @SerializedName("forks")
    @Expose
    private var forks: Int,

    @SerializedName("currentPeriodStars")
    @Expose
    private var currentPeriodStars: Int,

    @SerializedName("builtBy")
    @Expose
    var buildBylist: List<BuiltBy> = emptyList()

)