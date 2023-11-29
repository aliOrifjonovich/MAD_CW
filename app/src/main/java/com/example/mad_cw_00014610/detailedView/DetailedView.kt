package com.example.mad_cw_00014610.detailedView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mad_cw_00014610.data.AgroTechRepository
import com.example.mad_cw_00014610.R
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.example.mad_cw_00014610.reusablecomp.Navbar


@Composable
fun DetailedView(
    onAddNewMovieClick: () -> Unit,
    onHomeBtnClick: () -> Unit,
    onSaveDrafBtnClick: ()->Unit,
    agrotechId: String,
    viewModel: DetailedViewModel = DetailedViewModel(
        agrotechId, AgroTechRepository(), onHomeBtnClick
    )
) {

    var showDialog by remember { mutableStateOf(false) }
    val agrotech by viewModel.agroTechLiveData.observeAsState()
    val onDeleteClick: () -> Unit = {
        showDialog = true
    }

    val onConfirmDelete: () -> Unit = {
        viewModel.agroTechLiveData.value?.id?.let { agroTechId ->
            viewModel.deleteAgroTechById(agroTechId)
        }

    }

    if (agrotech != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.white))
                .padding(10.dp, 10.dp, 10.dp, 72.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {

            if(agrotech!!.imageurl != null){
                DetailViewImage(imageUrl = agrotech!!.imageurl)
            }

            MyDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Name(name = agrotech!!.name)

                if (agrotech!!.releaseDate != null) {
                    ReleaseDate(releaseDate = agrotech!!.releaseDate!!)
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            if (agrotech!!.budget != null) {
                Budget(budget = agrotech!!.budget!!)
            }

            if (agrotech!!.description != null) {
                Description(description = agrotech!!.description!!)
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
                if (!agrotech!!.owners.isNullOrEmpty()) {
                    Owners(owners = agrotech!!.owners!!)
                }
            }

            DeleteConfirmationDialog(
                showDialog = showDialog,
                onConfirm = onConfirmDelete,
                onDismiss = { showDialog = false }
            )

            //Buttons
            Row(
                modifier = Modifier.padding(top=10.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .size(190.dp, 50.dp)
                        .background(
                            colorResource(id = R.color.bleak_green_light),
                            shape = RoundedCornerShape(15.dp)
                        )
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
                        .background(
                            colorResource(id = R.color.dark_red_btn),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .clickable(onClick = onDeleteClick)
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
    Box(modifier = Modifier.fillMaxWidth()){
        Box(modifier = Modifier.align(Alignment.BottomCenter)){
            Navbar(  onAddNewMovieClick, onHomeBtnClick, onSaveDrafBtnClick )
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
private fun Owners(owners: List<String>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        var i = 0
        for (owner in owners) {
            OwnerTextView(owner = owner, ++i == owners.size)
        }
    }
}

@Composable
private fun OwnerTextView(owner: String, isTheLastOne: Boolean) {
    Text(
        modifier = Modifier.padding(3.dp, 1.dp),
        text = if (isTheLastOne) owner else "$owner,",
        color = Color.DarkGray,
        fontSize = 19.sp,
        fontFamily = FontFamily.SansSerif,
        fontStyle = FontStyle.Italic
    )
}


@Composable
private fun MyDivider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp),
        color = Color.LightGray

    )
}
@Composable
private fun DetailViewImage(imageUrl: String?){
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(id = R.string.image_error_message),
        modifier = Modifier
            .fillMaxSize()
            .widthIn(min = 0.dp, max = Dp.Infinity)
            .border(width = 1.dp, color = Color.White)
            .height(300.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun DeleteConfirmationDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { DeleteIcon() },
            text = { TextInfo() },
            confirmButton = {
                Button(
                    onClick = {
                        onConfirm()
                        onDismiss()
                    },
                    modifier= Modifier
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = "Delete", modifier = Modifier.width(80.dp).padding(start = 10.dp), fontSize=19.sp)
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss,
                    modifier= Modifier
                        .padding(vertical = 8.dp)
                ) {
                    Text(text = "Cancel", modifier = Modifier.width(80.dp).padding(start = 10.dp), fontSize=19.sp)
                }
            }
        )
    }
}

@Composable
private  fun DeleteIcon(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id =  R.drawable.outline_delete_24),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun TextInfo(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "Are you sure you want to delete?",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}
