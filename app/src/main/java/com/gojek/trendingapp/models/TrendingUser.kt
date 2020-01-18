package com.gojek.trendingapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class TrendingUser(

    @SerializedName("author")
    @Expose
     var author: String,

    @SerializedName("name")
    @Expose var name: String,

    @SerializedName("avatar")
    @Expose
     var avatar: String,

    @SerializedName("url")
    @Expose
     var url: String,

    @SerializedName("description")
    @Expose
     var description: String,

    @SerializedName("language")
    @Expose
     var language: String,

    @SerializedName("languageColor")
    @Expose
     var languageColor: String,

    @SerializedName("stars")
    @Expose
     var stars: Int,

    @SerializedName("forks")
    @Expose
     var forks: Int,

    @SerializedName("currentPeriodStars")
    @Expose
     var currentPeriodStars: Int,

    @SerializedName("builtBy")
    @Expose
    var buildBylist: List<BuiltBy> = emptyList(),

    var expanded: Boolean = false
)