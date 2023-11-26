package com.example.mad_cw_00014610.addNew

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_cw_00014610.data.AgroTechRepository
import com.example.mad_cw_00014610.data.dataClasses.AgroTech
import com.example.mad_cw_00014610.data.network.MyResponse
import kotlinx.coroutines.launch

class AddNewAgroTechViewModel(private val movieRepository: AgroTechRepository) : ViewModel() {

    val insertResponseLiveData: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }

    fun saveNewMovie(movie: AgroTech) {
        viewModelScope.launch {
            try {

                val response = movieRepository.insertNewMovie(movie)
                insertResponseLiveData.value = response

                Log.d("Update_response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}