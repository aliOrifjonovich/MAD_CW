package com.example.mad_cw_00014610.detailedView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_cw_00014610.data.AgroTechRepository
import com.example.mad_cw_00014610.data.dataClasses.AgroTech
import kotlinx.coroutines.launch

class DetailedViewModel(
    agrotechId: String,
    private val agroTechRepository: AgroTechRepository
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
                val movie = agroTechRepository.getAgroTechById(agrotechId)
                agroTechLiveData.value = movie
            }
        }
    }

}