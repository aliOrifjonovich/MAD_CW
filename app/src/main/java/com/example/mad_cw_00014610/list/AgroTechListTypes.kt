package com.example.mad_cw_00014610.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad_cw_00014610.R

val imageList = listOf(
    R.drawable.typeimage1,
    R.drawable.typeimage2,
    R.drawable.typeimage1,
    R.drawable.typeimage2,
    R.drawable.typeimage1,
    R.drawable.typeimage2,
)
@Composable
fun AgroTechListTypes() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 80.dp, 0.dp, 90.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(
                stringResource(id = R.string.product_types),
                modifier = Modifier.padding(10.dp, 10.dp),
                fontFamily = jostFont,
                color = colorResource(id = R.color.black),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
             LazyRow{
                items(imageList) { imageResId ->
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = null,
                        modifier = Modifier
                            .width(100.dp)
                            .height(80.dp)
                            .padding(horizontal = 5.dp)
                            .border(
                                width = 2.dp,
                                color = colorResource(id = R.color.green_dark),
                                shape = RoundedCornerShape(
                                    topStart = 8.dp,
                                    topEnd = 8.dp,
                                    bottomStart = 8.dp,
                                    bottomEnd = 8.dp
                                )
                            )
                    )
                }
            }
        }
    }
}