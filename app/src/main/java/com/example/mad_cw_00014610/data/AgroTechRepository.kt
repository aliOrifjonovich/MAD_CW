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
    suspend fun getAgroTechList(): List<AgroTech> {
        val agroteches = mutableListOf<AgroTech>()

        try {
            val response: MyListResponse<AgroTechResponse> =
                RetrofitInstance.agrotechService.getAllEquipments("00014610")
            val agroTechesFromResponse = response.data

            if (agroTechesFromResponse != null) {

                for (agroTechFromResponse in agroTechesFromResponse) {
                    if (agroTechFromResponse.description != null) {
                        agroteches.add(
                            AgroTech(
                                id = agroTechFromResponse.id.toString(),
                                name = agroTechFromResponse.name.uppercase(),
                                description = agroTechFromResponse.description,
                                budget= agroTechFromResponse.budget,
                                releaseDate = agroTechFromResponse.releaseDate,
                                imageurl = agroTechFromResponse.imageurl,
                            )
                        )
                    }
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return agroteches
    }

    suspend fun insertNewEquipment(agroTech: AgroTech): MyResponse? {
        val response: MyResponse

        try {
            val agroTechRequest =
                AgroTechRequest(
                    name = agroTech.name,
                    description = agroTech.description,
                    owners = agroTech.owners,
                    budget = agroTech.budget,
                    releaseDate = agroTech.releaseDate,
                    imageurl = agroTech.imageurl
                )

            response = RetrofitInstance.agrotechService.insertNewEquipment(
                "00014610",
                agroTechRequest
            )

            Log.d("Update_response", response.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

        return response
    }

    suspend fun getAgroTechById(agrotechId: String): AgroTech? {
        try {
            val response: MyItemResponse<AgroTechResponse> =
                RetrofitInstance.agrotechService.getOneAgroTechById(agrotechId, "00014610")
            val agroTechFromResponse = response.data

            if (agroTechFromResponse != null) {
                if (agroTechFromResponse.description != null
                ) {
                    return AgroTech(
                        id = agrotechId,
                        name = agroTechFromResponse.name,
                        description = agroTechFromResponse.description,
                        owners = extractListOfActorsFromResponse(agroTechFromResponse.owners),
                        budget = agroTechFromResponse.budget,
                        releaseDate = formatReleaseDate(agroTechFromResponse.releaseDate),
                        imageurl = agroTechFromResponse.imageurl
                    )
                }
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return null
        }
        return null
    }

    suspend fun deleteAgroTechById(agroTechId: String): MyResponse? {
        val response: MyResponse
        try {
            response = RetrofitInstance.agrotechService.deleteOneAgroTechById(agroTechId, "00014610")

            Log.d("Update_response", response.toString())
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
        return response
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

