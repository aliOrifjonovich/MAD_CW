package com.example.mad_cw_00014610.data.network.agroTech

import com.google.gson.annotations.SerializedName

data class AgroTechResponseActorItem(
    @SerializedName("id")
    val actorEntryId: String,
    @SerializedName("record_id")
    val movieRecordId: String,
    @SerializedName("value")
    val actorName: String
)