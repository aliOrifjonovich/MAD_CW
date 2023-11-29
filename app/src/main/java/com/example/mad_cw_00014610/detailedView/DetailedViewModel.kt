package com.example.mad_cw_00014610.detailedView

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_cw_00014610.data.AgroTechRepository
import com.example.mad_cw_00014610.data.dataClasses.AgroTech
import kotlinx.coroutines.launch

class DetailedViewModel(
    agrotechId: String,
    private val agroTechRepository: AgroTechRepository,
    private val onHomeBtnClick: () -> Unit
    ) : ViewModel() {

    val agroTechLiveData: MutableLiveData<AgroTech> by lazy {
        MutableLiveData<AgroTech>()
    }

    init {
        getAgroTechById(agrotechId)
    }

    private fun getAgroTechById(agrotechId: String) {
        viewModelScope.launch {
            if (!agrotechId.isNullOrEmpty()) {
                val agroTech = agroTechRepository.getAgroTechById(agrotechId)
                agroTechLiveData.value = agroTech
            }
        }
    }

    fun deleteAgroTechById(agroTechId: String) {
        viewModelScope.launch {
            try {

                val deleted = agroTechRepository.deleteAgroTechById(agroTechId)

                Log.d("deleted_response", deleted.toString())
                onHomeBtnClick()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



}