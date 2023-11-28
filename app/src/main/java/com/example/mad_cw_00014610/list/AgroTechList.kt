package com.example.mad_cw_00014610.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.layout.ContentScale
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
import com.example.mad_cw_00014610.reusablecomp.Navbar
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

        val teches by viewModel.agroTechLiveData.observeAsState()

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

     Box(modifier = Modifier.align(Alignment.BottomCenter)){
         Navbar( onAddNewMovieClick, onHomeBtnClick, onSaveDrafBtnClick )
     }
    }
}

//Each item card UI
@Composable
private fun AgroTechItem(tech: AgroTech, onMovieClick: (String) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
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
            defaultElevation = 8.dp
        ),
    )
    {
        Column(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable {
                    onMovieClick(tech.id)
                }

        ) {
            AgroTechImage(imageUrl = tech.imageurl)
            AgroTechItemName(name = tech.name)
            ReleaseDate(releaseDate = tech.releaseDate ?: "")
            if (tech.budget != null) {
                AgroTechItemPrise(budget = tech.budget)
            } else {
                Text(text = "No Budget Information", color = Color.Gray)
            }
        }
    }
}

@Composable
private fun AgroTechItemName(name: String) {
    Text(
        text = name,
        fontSize = 16.sp,
        fontFamily = jostFont,
        textAlign = TextAlign.Left,
        modifier = Modifier.padding(0.dp, 12.dp, 0.dp, 2.dp)
    )
}
@Composable
private fun ReleaseDate(releaseDate: String) {
    val year = if (releaseDate.isNotEmpty()) {
        releaseDate.substring(0, 4)
    } else {
        "No Release Date"
    }

    Text(
        modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 5.dp),
        text = stringResource(id = R.string.detailed_view_release_date_label, year),
        color = Color.DarkGray,
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif
    )
}
@Composable
private fun AgroTechItemPrise(budget: Int) {
    Text(
        text = stringResource(id = R.string.detailed_view_price_label, budget),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        color = Color.Black,
        fontSize = 21.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun AgroTechImage(imageUrl: String?) {
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(id = R.string.image_error_message),
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f),
        contentScale = ContentScale.Crop
    )
}


