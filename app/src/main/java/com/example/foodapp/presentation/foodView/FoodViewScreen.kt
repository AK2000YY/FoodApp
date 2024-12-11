package com.example.foodapp.presentation.foodView

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodapp.core.classes.Utils
import com.example.foodapp.core.component.CustomProgressBar
import com.example.foodapp.domain.model.NutritionalValue
import com.example.foodapp.domain.model.Response
import com.example.foodapp.navigation.sharedData.SharedViewModel
import com.example.foodapp.presentation.foodView.component.ImagePart

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FoodViewScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = hiltViewModel(),
    foodViewViewModel: FoodViewViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        ImagePart(
            modifier = Modifier
                .height(300.dp),
            bitmap = state.image!!,
            onSave = {
                viewModel.saveFood()
            }
        )
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(15.dp),
                    color = Color.Black
                ),
            contentAlignment = Alignment.Center
        ) {
            when(val response = foodViewViewModel.response) {
                is Response.Success ->
                    if(!response.data) CustomProgressBar()
                    else
                        NutritionalValueDetail(
                            nutritionalValue = foodViewViewModel.nutritionalValue,
//                            modifier = Modifier.fillMaxSize()
                        )
                is Response.Loading ->
                    CustomProgressBar()
                is Response.Failure ->
                    Text("something is error")
            }
        }
    }

    LaunchedEffect(Unit) {
        foodViewViewModel.findItemByName(state.name!!)
    }

    when(val response = viewModel.response) {
        is Response.Success ->
            LaunchedEffect(response.data) {
                if(response.data)
                    Utils.showMessage(context, "save successful")
            }
        is Response.Loading ->
            CustomProgressBar()
        is Response.Failure ->
            LaunchedEffect(response.e) {
                Utils.showMessage(context, response.e.message)
            }
    }
}


@Composable
fun NutritionalValueDetail(
    nutritionalValue: NutritionalValue,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        NutritionalValueOne(firstText = "Name" , secondText = nutritionalValue.name!!)
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.Black))
        NutritionalValueOne(firstText = "Energy" , secondText = "${nutritionalValue.energy!!} kal")
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.Black))
        NutritionalValueOne(firstText = "Fats" , secondText = "${nutritionalValue.fats!!} g")
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.Black))
        NutritionalValueOne(firstText = "Saturated Fatty Acids" , secondText = "${nutritionalValue.saturatedFattyAcids!!} g")
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.Black))
        NutritionalValueOne(firstText = "Sugars" , secondText = "${nutritionalValue.sugars!!} g")
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
            .background(Color.Black))
        NutritionalValueOne(firstText = "Proteins" , secondText = "${nutritionalValue.proteins!!} g")
    }
}

@Composable
fun NutritionalValueOne(
    firstText: String,
    secondText: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = firstText,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(2.dp)
                .background(Color.Black)
        )
        Text(
            text = secondText,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        )
    }
}