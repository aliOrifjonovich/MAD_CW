package com.example.movielist.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mad_cw_00014610.data.AgroTechRepository
import com.example.mad_cw_00014610.data.dataClasses.AgroTech
import kotlinx.coroutines.launch

class AgroTechViewModal(
    private val agroTechRepository: AgroTechRepository
) : ViewModel() {

    val agroTechLiveData: MutableLiveData<List<AgroTech>> by lazy {
        MutableLiveData<List<AgroTech>>()
    }

    init {
        getAllitems()
    }

    fun getAllitems() {
        viewModelScope.launch {
            val agroTeches = agroTechRepository.getAgroTechList()
            agroTechLiveData.value = agroTeches
        }
    }
}

