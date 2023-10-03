package com.example.realm.main.background.response


import com.google.gson.annotations.SerializedName

 class Result{
     @SerializedName("climate")
     var climate: String? = ""
     @SerializedName("created")
     var created: String?= ""
     @SerializedName("diameter")
     var diameter: String?= ""
     @SerializedName("edited")
     var edited: String?= ""
     @SerializedName("films")
     var films: List<String?>?= null
     @SerializedName("gravity")
     var gravity: String?= ""
     @SerializedName("name")
     var name: String?= ""
     @SerializedName("orbital_period")
     var orbitalPeriod: String?= ""
     @SerializedName("population")
     var population: String?= ""
     @SerializedName("residents")
     var residents: List<String?>?= null
     @SerializedName("rotation_period")
     var rotationPeriod: String?= ""
     @SerializedName("surface_water")
     var surfaceWater: String?= ""
     @SerializedName("terrain")
     var terrain: String?= ""
     @SerializedName("url")
     var url: String?= ""

 }

