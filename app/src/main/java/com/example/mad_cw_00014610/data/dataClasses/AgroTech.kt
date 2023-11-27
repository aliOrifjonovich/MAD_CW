package com.example.mad_cw_00014610.data.dataClasses

data class AgroTech(
    val id: String = "",
    val name: String,
    val description: String?,
    val owners: List<String>? = emptyList(),
    val budget: Int? = null,
    val releaseDate: String? = null,
    val imageurl: String?,
)
