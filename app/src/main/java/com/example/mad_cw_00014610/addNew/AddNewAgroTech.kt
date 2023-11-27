package com.example.mad_cw_00014610.addNew

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.mad_cw_00014610.MainActivity
import com.example.mad_cw_00014610.R
import com.example.mad_cw_00014610.data.AgroTechRepository
import com.example.mad_cw_00014610.data.dataClasses.AgroTech
import java.text.ParseException
import java.text.SimpleDateFormat


@Composable
fun AddNewAgroTech(
    viewModel: AddNewAgroTechViewModel = AddNewAgroTechViewModel(AgroTechRepository())
) {
    val localContext = LocalContext.current
    val navController = rememberNavController()
    val name = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val budget = remember { mutableStateOf("") }
    val releaseDate = remember { mutableStateOf("") }
    val actors = remember { mutableStateOf("") }
    val ratingOptions = listOf("1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5")
    val isRatingExpanded = remember {
        mutableStateOf(false)
    }
    val selectedRatingText = remember { mutableStateOf(ratingOptions[0]) }

    val response by viewModel.insertResponseLiveData.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier= Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.bleak_green_light))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CreateNewMoviePageTitle()

                Spacer(modifier = Modifier.weight(1f))

                // "Go to Home" button
                Button(
                    onClick = {
                        navController.navigate("agroteches_list")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape= RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.bleak_green_light),
                        contentColor = Color.White
                    )
                ) {
                    GoToHomeIcon()
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(15.dp))
                NameInput(name = name.value, onNameChange = { name.value = it })
                Spacer(Modifier.height(16.dp))
                DescriptionInput(description = description.value,
                    onDescriptionChange = { description.value = it })
                Spacer(modifier = Modifier.height(15.dp))
                Budget(budget = budget.value, onBudgetChanged = { budget.value = it })
                ReleaseDate(releaseDate = releaseDate.value,
                    onReleaseDateChanged = { releaseDate.value = it })
                Spacer(modifier = Modifier.height(15.dp))
                OwnersInput(actors = actors.value, onActorsChange = { actors.value = it })

                // actors text input - comma separated 4x

                Spacer(Modifier.height(16.dp))
                AddNewButton {
                    val constructedMovie: AgroTech? = constructMovieIfInputValid(
                        nameInput = name.value,
                        descriptionInput = description.value,
                        budgetInput = budget.value,
                        releaseDateInput = releaseDate.value,
                        actorsInput = actors.value,
                        ratingInput = selectedRatingText.value,
                        context = localContext
                    )

                    if (constructedMovie != null
                    ) {
                        viewModel.saveNewMovie(
                            constructedMovie
                        )
                    }
                }
            }
        }
      

        if (response != null) {
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .align(Alignment.Center),
                fontSize = 19.sp,
                text = if (response!!.status == "OK") stringResource(id = R.string.saved_success_msg)
                else stringResource(id = R.string.saved_fail_msg)
            )

            if (response!!.status == "OK") {
                localContext.startActivity(Intent(localContext, MainActivity::class.java))
            }
        }
    }

}

@Composable
private fun CreateNewMoviePageTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Expand to full width
            .background(color = colorResource(id = R.color.bleak_green_light))
            .padding(vertical = 50.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.title_activity_add_new_equipment),
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NameInput(name: String, onNameChange: (String) -> Unit) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .border(
            width = 2.dp,
            color = colorResource(id = R.color.bleak_green_light),
            shape = RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 8.dp,
                bottomStart = 8.dp,
                bottomEnd = 8.dp
            )
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.white)
        ),
        value = name,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onNameChange(it) },
        label = {
            Text(stringResource(id = R.string.equipment_name_input_hint))
        })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DescriptionInput(description: String, onDescriptionChange: (String) -> Unit) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .border(
            width = 2.dp,
            color = colorResource(id = R.color.bleak_green_light),
            shape = RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 8.dp,
                bottomStart = 8.dp,
                bottomEnd = 8.dp
            )
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.white)
        ),
        value = description,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onDescriptionChange(it) },
        label = {
            Text(stringResource(id = R.string.equipment_desc_input_hint))
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Budget(budget: String, onBudgetChanged: (String) -> Unit) {
    TextField(modifier = Modifier
        .fillMaxWidth()
        .border(
            width = 2.dp,
            color = colorResource(id = R.color.bleak_green_light),
            shape = RoundedCornerShape(
                topStart = 8.dp,
                topEnd = 8.dp,
                bottomStart = 8.dp,
                bottomEnd = 8.dp
            )
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black, containerColor = colorResource(id = R.color.white)
        ),
        value = budget,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { onBudgetChanged(it) },
        label = {
            Text(stringResource(id = R.string.equipment_budget_input_hint),  fontSize = 20.sp,)
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReleaseDate(releaseDate: String, onReleaseDateChanged: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.bleak_green_light),
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                )
            )
    ) {
        Text(
            modifier = Modifier.padding(5.dp),
            text = stringResource(id = R.string.equipment_release_date_input_label),
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif
        )

        TextField(modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                containerColor = colorResource(id = R.color.white)
            ),
            value = releaseDate,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { onReleaseDateChanged(it) },
            label = {
                Text(stringResource(id = R.string.equipment_date_input_hint))
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OwnersInput(actors: String, onActorsChange: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.bleak_green_light),
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                )
            ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = colorResource(id = R.color.white)
        ),
        value = actors,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { onActorsChange(it) },
        label = {
            Text(stringResource(id = R.string.add_new_owners_input_hint))
        }
    )
}

@Composable
private fun AddNewButton(onClick: () -> Unit) {
    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .padding(vertical = 16.dp),
        shape= RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.bleak_green_light), contentColor = Color.White
        )

    ) {
        Text(
            fontSize = 20.sp, text = stringResource(id = R.string.save_btn_text)
        )
    }
}

private fun constructMovieIfInputValid(
    nameInput: String?,
    descriptionInput: String?,
    budgetInput: String?,
    releaseDateInput: String?,
    actorsInput: String?,
    ratingInput: String?,
    context: Context
): AgroTech? {
    if (nameInput.isNullOrEmpty() ||
        descriptionInput.isNullOrEmpty() ||
        budgetInput.isNullOrEmpty() ||
        releaseDateInput.isNullOrEmpty() ||
        actorsInput.isNullOrEmpty() ||
        ratingInput.isNullOrEmpty()
    ) {
        Toast.makeText(
            context, context.resources.getString(R.string.movie_all_fields_compulsory_warning),
            Toast.LENGTH_SHORT
        ).show()
        return null
    }

    val dateFormat = SimpleDateFormat("YYYY-MM-DD")

    try {
        dateFormat.parse(releaseDateInput)
    } catch (e: ParseException) {
        Toast.makeText(
            context, context.resources.getString(R.string.movie_date_format_incorrect_warning),
            Toast.LENGTH_SHORT
        ).show()
        return null
    }

    return AgroTech(
        name = nameInput,
        description = descriptionInput,
        actors = actorsInput.split(","),
        budget = budgetInput.toInt(),
        rating = ratingInput.toDouble(),
        releaseDate = "$releaseDateInput 00:00:00"
    )
}

@Composable
fun GoToHomeIcon(){
    Image(painter = painterResource(id = R.drawable.outline_west_24),
        contentDescription = stringResource(id = R.string.btn_go_draf_list)
    )
}