package com.example.mad_cw_00014610.list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad_cw_00014610.R

@Composable
fun AgroTechListHeader() {
   Box(
      modifier = Modifier
         .fillMaxSize()
         .padding(0.dp, 10.dp)
   ){
      Row(
         modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.TopCenter),

         verticalAlignment = Alignment.CenterVertically,
         horizontalArrangement = Arrangement.SpaceBetween
      ) {
         Text(
            stringResource(id = R.string.app_name),
            modifier = Modifier.padding(10.dp, 10.dp),
            fontFamily = jostFont,
            color = colorResource(id = R.color.green_dark),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
         )
        Box(
           modifier = Modifier
              .padding(10.dp, 5.dp)
              .border(
                   width = 3.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp)
              )
        ){
           Text(
              stringResource(id = R.string.name_user),
              modifier = Modifier
                 .padding(18.dp, 10.dp),
              fontFamily = jostFont,
              color = colorResource(id = R.color.black),
              fontSize = 23.sp,
              fontWeight = FontWeight.Bold,

              )
        }
      }
   }
}