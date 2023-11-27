package com.example.mad_cw_00014610.detailedView

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad_cw_00014610.data.AgroTechRepository
import com.example.mad_cw_00014610.R


@Composable
fun DetailedView(
    onAddNewMovieClick: () -> Unit,
    agrotechId: String,
    viewModel: DetailedViewModel = DetailedViewModel(agrotechId, AgroTechRepository())
) {

    val movie by viewModel.movieLiveData.observeAsState()

    if (movie != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white))
                .padding(16.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Image(
                painterResource(R.drawable.detailimage),
                stringResource(id = R.string.detail_image_desc),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )

            MyDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Name(name = movie!!.name)

                if (movie!!.releaseDate != null) {
                    ReleaseDate(releaseDate = movie!!.releaseDate!!)
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            if (movie!!.budget != null) {
                Budget(budget = movie!!.budget!!)
            }

            if (movie!!.description != null) {
                Description(description = movie!!.description!!)
            }

            Spacer(Modifier.height(10.dp))

            Column(){
                Text(
                    modifier = Modifier.padding(bottom = 3.dp),
                    text = stringResource(id = R.string.owner_detail_content),
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                )
                if (!movie!!.actors.isNullOrEmpty()) {
                    Actors(actors = movie!!.actors!!)
                }
            }

            //Buttons
            Row(
                modifier = Modifier.padding(top=10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .size(190.dp, 50.dp)
                        .background(colorResource(id = R.color.bleak_green_light), shape = RoundedCornerShape(15.dp))
                        .clickable(onClick = onAddNewMovieClick)
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 3.dp),
                        text = stringResource(id = R.string.update_button),
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier
                        .size(190.dp, 50.dp)
                        .background(colorResource(id = R.color.dark_red_btn), shape = RoundedCornerShape(15.dp))
                        .clickable(onClick = onAddNewMovieClick)
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        modifier = Modifier.padding(bottom = 3.dp),
                        text = stringResource(id = R.string.delete_button),
                        color = Color.White,
                        fontSize = 15.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }

        }
    }
}

@Composable
private fun Name(name: String) {
    Text(
        modifier= Modifier.width(248.dp),
        text = name,
        color = Color.Black,
        fontSize = 19.sp,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Left
    )
}

@Composable
private fun Budget(budget: Int) {
    Text(
        modifier = Modifier.padding(top=10.dp, bottom = 3.dp),
        text = stringResource(id = R.string.detailed_view_price_label, budget),
        color = Color.Black,
        fontSize = 21.sp,
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
    )
}


@Composable
private fun ReleaseDate(releaseDate: String) {
    Text(
        modifier = Modifier.padding(bottom = 3.dp),
        text = stringResource(id = R.string.detailed_view_release_date_label, releaseDate),
        color = Color.Black,
        fontSize = 15.sp,
        fontFamily = FontFamily.SansSerif
    )
}

@Composable
private fun Description(description: String) {
    Text(
        modifier = Modifier.padding(top = 10.dp),
        text = description,
        color = Color.DarkGray,
        fontSize = 20.sp,
        fontFamily = FontFamily.SansSerif
    )
}


@Composable
private fun Actors(actors: List<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        var i = 0
        for (actor in actors) {
            ActorTextView(actor = actor, ++i == actors.size)
        }
    }
}

@Composable
private fun ActorTextView(actor: String, isTheLastOne: Boolean) {
    Text(
        modifier = Modifier.padding(3.dp, 1.dp),
        text = if (isTheLastOne) actor else "$actor,",
        color = Color.DarkGray,
        fontSize = 19.sp,
        fontFamily = FontFamily.SansSerif,
        fontStyle = FontStyle.Italic
    )
}


@Composable
private fun MyDivider() {
    Divider(
        modifier = Modifier.fillMaxWidth().padding(0.dp, 10.dp),
        color = Color.LightGray

    )
}