package com.example.mad_cw_00014610.data.network

import com.google.gson.annotations.SerializedName

class MyItemResponse<T> (@SerializedName("data")
                         val data: T?) : MyResponse()
