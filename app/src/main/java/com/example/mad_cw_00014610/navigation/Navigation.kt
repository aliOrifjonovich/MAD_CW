package com.example.mad_cw_00014610.navigation

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mad_cw_00014610.MainActivity
import com.example.mad_cw_00014610.addNew.AddNewActivity
import com.example.mad_cw_00014610.detailedView.DetailedView
import com.example.mad_cw_00014610.list.AgroTechesList

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = Screens.AgroTechesListScreen.route) {
        composable(Screens.AgroTechesListScreen.route) {
            AgroTechesList(
                onAddNewMovieClick = {
                context.startActivity(Intent(context, AddNewActivity::class.java))
                 },
                onHomeBtnClick ={context.startActivity(Intent(context, MainActivity::class.java))},
                onSaveDrafBtnClick={},
                onMovieClick = { agrotechId ->
                    navController.navigate("detailedView/$agrotechId")
                }
            )
        }

//        composable(Screens.PlayerPageScreen.route) {
//            PlayerScreen()
//        }

        composable(
            route = "detailedView/{agrotechId}"
        ) { backStackEntry ->
            DetailedView(
                agrotechId = backStackEntry.arguments?.getString("agrotechId")!!,
                onAddNewMovieClick={ context.startActivity(Intent(context, AddNewActivity::class.java))},
                onHomeBtnClick ={context.startActivity(Intent(context, MainActivity::class.java))},
                onSaveDrafBtnClick={},
            )
        }
    }
}