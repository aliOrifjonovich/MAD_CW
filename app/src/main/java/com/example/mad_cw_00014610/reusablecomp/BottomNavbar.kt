package com.example.mad_cw_00014610.reusablecomp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad_cw_00014610.R
import com.example.mad_cw_00014610.list.jostFont

@Composable
fun Navbar(
    onAddNewMovieClick: ()->Unit,
    onHomeBtnClick: () -> Unit,
    onSaveDrafBtnClick: ()->Unit
){
    //Navigation
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.green_dark))
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            //Home button in navbar
            Column(
                modifier = Modifier
                    .clickable(onClick = onHomeBtnClick),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeIcon()
                Text(
                    stringResource(id = R.string.btn_go_products_list),
                    //modifier = Modifier.padding(5.dp, 5.dp),
                    fontFamily = jostFont,
                    color = colorResource(id = R.color.white),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }


            // Add icon navbar button
            Column(
                modifier = Modifier
                    .clickable(onClick = onAddNewMovieClick),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AddIcon()
            }



            //Saved Draf navbar button
            Column(
                modifier = Modifier
                    .clickable(onClick = onSaveDrafBtnClick),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SaveDrafIcon()
                Text(
                    stringResource(id = R.string.btn_go_draf_list),
                    //modifier = Modifier.padding(5.dp, 5.dp),
                    fontFamily = jostFont,
                    color = colorResource(id = R.color.white),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

//Navigation icon functions
@Composable
fun AddIcon(){
    Image(
        modifier = Modifier.size(60.dp),
        painter = painterResource(id = R.drawable.baseline_add_circle_24),
        contentDescription = stringResource(id = R.string.btn_add_new_icon)
    )
}
@Composable
fun HomeIcon(){
    Image(
        modifier = Modifier.size(40.dp),
        painter = painterResource(id = R.drawable.outline_home_24),
        contentDescription = stringResource(id = R.string.btn_go_products_list)
    )
}

@Composable
fun SaveDrafIcon(){
    Image(
        modifier = Modifier.size(40.dp),
        painter = painterResource(id = R.drawable.outline_shopping_bag_24),
        contentDescription = stringResource(id = R.string.btn_go_draf_list)
    )
}