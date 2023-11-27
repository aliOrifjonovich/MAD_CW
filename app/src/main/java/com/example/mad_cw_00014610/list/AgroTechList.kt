package com.example.mad_cw_00014610.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
        AgroTechListHeader()
        AgroTechListTypes()

        val teches by viewModel.moviesLiveData.observeAsState()

        //Map all items from getter and set values to the items
        if (!teches.isNullOrEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(0.dp, 220.dp, 0.dp, 72.dp)
            ) {
                items(items = teches!!.toList(), itemContent = { item ->
                    AgroTechItem(tech = item, onMovieClick)
                })
            }
        }

        //Navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
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
}

//Each item card UI
@Composable
private fun AgroTechItem(tech: AgroTech, onMovieClick: (String) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(12.dp)
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.green_dark),
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                )
            ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white), //Card background color
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
                    onMovieClick(tech.id)
                }

        ) {
            AgroTechImage(imageUrl = tech.imageurl)

            MovieItemName(name = tech.name)
            if (!tech.description.isNullOrEmpty())
                MovieItemDesc(desc = tech.description)
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
fun AgroTechImage(imageUrl: String?) {
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(id = R.string.image_error_message),
        modifier = Modifier.fillMaxSize()
    )
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

