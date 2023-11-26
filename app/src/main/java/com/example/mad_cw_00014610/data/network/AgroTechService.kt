package com.example.mad_cw_00014610.data.network

import com.example.mad_cw_00014610.data.network.agroTech.AgroTechRequest
import com.example.mad_cw_00014610.data.network.agroTech.AgroTechResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AgroTechService {
    @GET("records/all")
    suspend fun getAllMovies(
        @Query("student_id") student_id: String
    ): MyListResponse<AgroTechResponse>

    @POST("records")
    suspend fun insertNewMovie(
        @Query("student_id") student_id: String,
        @Body movieRequest: AgroTechRequest
    ): MyResponse

    @GET("records/{record_id}")
    suspend fun getOneMovieById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyItemResponse<AgroTechResponse>
}