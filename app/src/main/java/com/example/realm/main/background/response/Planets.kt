package com.example.realm.main.background.response


import com.example.realm.main.background.response.Result
import com.google.gson.annotations.SerializedName

data class Planets(
    @SerializedName("count")
    var count: Int?,
    @SerializedName("next")
    var next: String?,
    @SerializedName("previous")
    var previous: Any?,
    @SerializedName("results")
    var results: ArrayList<Result>
)