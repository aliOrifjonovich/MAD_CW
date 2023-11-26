package com.example.mad_cw_00014610.navigation

sealed class Screens(val route: String) {
    object AgroTechesListScreen: Screens("agroteches_list")
}
