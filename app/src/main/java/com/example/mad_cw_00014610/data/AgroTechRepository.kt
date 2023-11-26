package com.example.mad_cw_00014610.data

import android.util.Log
import com.example.mad_cw_00014610.data.dataClasses.AgroTech
import com.example.mad_cw_00014610.data.network.MyItemResponse
import com.example.mad_cw_00014610.data.network.MyListResponse
import com.example.mad_cw_00014610.data.network.MyResponse
import com.example.mad_cw_00014610.data.network.RetrofitInstance
import com.example.mad_cw_00014610.data.network.agroTech.AgroTechRequest
import com.example.mad_cw_00014610.data.network.agroTech.AgroTechResponse
import com.example.mad_cw_00014610.data.network.agroTech.AgroTechResponseActorItem


class AgroTechRepository {
    suspend fun getMovieList(): List<AgroTech> {
        val movies = mutableListOf<AgroTech>()

        try {
            val response: MyListResponse<AgroTechResponse> =
                RetrofitInstance.movieService.getAllMovies("movie")
            val moviesFromResponse = response.data

            if (moviesFromResponse != null) {

                for (movieFromResponse in moviesFromResponse) {
                    if (movieFromResponse.description != null) {
                        movies.add(
                            AgroTech(
                                id = movieFromResponse.id.toString(),
                                name = movieFromResponse.name.uppercase(),
                                description = movieFromResponse.description
                            )
                        )
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return movies
    }

    suspend fun insertNewMovie(movie: AgroTech): MyResponse? {
        val response: MyResponse

        try {
            val movieRequest =
                AgroTechRequest(
                    name = movie.name,
                    description = movie.description,
                    actors = movie.actors,
                    budget = movie.budget,
                    rating = movie.rating,
                    releaseDate = movie.releaseDate
                )

            response = RetrofitInstance.movieService.insertNewMovie(
                "movie",
                movieRequest
            )

            Log.d("Update_response", response.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return response
    }

    suspend fun getMovieById(movieId: String): AgroTech? {
        try {
            val response: MyItemResponse<AgroTechResponse> =
                RetrofitInstance.movieService.getOneMovieById(movieId, "movie")
            val movieFromResponse = response.data

            if (movieFromResponse != null) {
                if (movieFromResponse.description != null
                ) {
                    return AgroTech(
                        id = movieId,
                        name = movieFromResponse.name,
                        description = movieFromResponse.description,
                        actors = extractListOfActorsFromResponse(movieFromResponse.actors),
                        budget = movieFromResponse.budget,
                        rating = movieFromResponse.rating,
                        releaseDate = formatReleaseDate(movieFromResponse.releaseDate)
                    )
                }
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }

    private fun extractListOfActorsFromResponse(
        actorsFromResponse: List<AgroTechResponseActorItem>
    ): List<String> {

        val myActors = mutableListOf<String>()

        for (actorObj in actorsFromResponse) {
            myActors.add(actorObj.actorName)
        }

        return myActors
    }

    private fun formatReleaseDate(releaseDate: String?): String {
        if (releaseDate.isNullOrEmpty()) return ""

        //release date will come in the format "YYYY-MM-DD HH:MM:SS". We should trim the "time" part
        //in Kotlin there is a special function for that, called "dropLast(number of characters to trim)"

        return releaseDate.dropLast(9)
    }
}

