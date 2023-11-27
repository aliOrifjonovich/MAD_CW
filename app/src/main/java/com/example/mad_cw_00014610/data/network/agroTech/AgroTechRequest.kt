package com.example.mad_cw_00014610.data.network.agroTech

import com.google.gson.annotations.SerializedName

data class AgroTechRequest(
    @SerializedName("title")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("text_list")
    val owners: List<String>?,
    @SerializedName("price") //the API has no "budget" field
    val budget: Int?,
    @SerializedName("date")
    val releaseDate: String?,
    @SerializedName("url")
    val imageurl: String?
)