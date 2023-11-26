package com.example.mad_cw_00014610.list

import android.graphics.PathIterator
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad_cw_00014610.R
import com.example.mad_cw_00014610.data.AgroTechRepository
import com.example.mad_cw_00014610.data.dataClasses.AgroTech
import com.example.movielist.list.AgroTechViewModal

val jostFont = FontFamily(Font(R.font.jost_regular))

@Composable
fun AgroTechesList(
    viewModel: AgroTechViewModal = AgroTechViewModal(AgroTechRepository()),
    onAddNewMovieClick: () -> Unit,
    onMovieClick: (String) -> Unit = {},
    onHomeBtnClick: () -> Unit,
    onSaveDrafBtnClick: ()->Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
            val movies by viewModel.moviesLiveData.observeAsState()

        if (!movies.isNullOrEmpty()) {
            LazyColumn(modifier = Modifier
                .fillMaxHeight()
                .padding(0.dp, 0.dp, 0.dp, 90.dp)) {
                items(items = movies!!.toList(), itemContent = { item ->
                    MovieItem(movie = item, onMovieClick)
                })
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(30.dp)
                .border(width = 1.dp, color=Color.Red),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            //Home button in navbar
            FloatingActionButton(
                modifier = Modifier,
                containerColor = colorResource(id = R.color.white),
                onClick = onHomeBtnClick
            ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 1.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HomeIcon()
                    Text(
                        stringResource(id = R.string.btn_go_products_list),
                        modifier = Modifier.padding(15.dp, 5.dp),
                        fontFamily = jostFont,
                        color = colorResource(id = R.color.black),
                        fontSize = 16.sp
                    )
                }
            }

            // Add icon navbar button
            FloatingActionButton(
                modifier = Modifier,
                containerColor = colorResource(id = R.color.white),
                onClick = onAddNewMovieClick
            ) {
                  AddIcon()
//                  Text(
//                      stringResource(id = R.string.btn_add_new_movie),
//                      modifier = Modifier.padding(15.dp, 5.dp),
//                      fontFamily = jostFont,
//                      color = colorResource(id = R.color.black),
//                      fontSize = 16.sp
//                  )
            }

            //Saved Draf navbar button
            FloatingActionButton(
                modifier = Modifier,
                containerColor = colorResource(id = R.color.white),
                onClick = onSaveDrafBtnClick
            ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 1.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SaveDrafIcon()
                    Text(
                        stringResource(id = R.string.btn_go_draf_list),
                        modifier = Modifier.padding(15.dp, 5.dp),
                        fontFamily = jostFont,
                        color = colorResource(id = R.color.black),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun MovieItem(movie: AgroTech, onMovieClick: (String) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.bleak_yellow), //Card background color
            contentColor = Color.DarkGray  //Card content color,e.g.text
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    )
    {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .clickable {
                    onMovieClick(movie.id)
                }
        ) {
            MovieItemName(name = movie.name)
            if (!movie.description.isNullOrEmpty())
                MovieItemDesc(desc = movie.description)
        }
    }
}

@Composable
private fun MovieItemName(name: String) {
    Text(
        text = name,
        fontSize = 21.sp,
        fontFamily = jostFont,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp)
    )
}

@Composable
private fun MovieItemDesc(desc: String) {
    Text(
        text = desc,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = Color.Gray,
        fontSize = 18.sp,
        fontFamily = jostFont,
        textAlign = TextAlign.Left
    )
}

@Composable
fun AddIcon(){
    Image(painter = painterResource(id = R.drawable.sharp_add_circle_24),
        contentDescription = stringResource(id = R.string.btn_add_new_icon)
    )
}
@Composable
fun HomeIcon(){
    Image(painter = painterResource(id = R.drawable.outline_home_24),
        contentDescription = stringResource(id = R.string.btn_go_products_list)
    )
}

@Composable
fun SaveDrafIcon(){
    Image(painter = painterResource(id = R.drawable.outline_home_24),
        contentDescription = stringResource(id = R.string.btn_go_draf_list)
    )
}

