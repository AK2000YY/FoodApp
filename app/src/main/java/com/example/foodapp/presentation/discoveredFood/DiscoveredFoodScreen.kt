package com.example.foodapp.presentation.discoveredFood

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.foodapp.domain.model.Response
import com.example.foodapp.presentation.discoveredFood.component.FoodCard

@Composable
fun DiscoveredFoodScreen(
    modifier: Modifier = Modifier,
    viewModel: DiscoveredFoodViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.foods.collectAsState()
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            items(state) {
                FoodCard(
                    modifier = Modifier
                        .padding(horizontal = 18.dp, vertical = 5.dp)
                        .height(120.dp)
                        .fillMaxWidth(),
                    image = it!!.image!!,
                    text = it.name!!,
                    date = it.date!!,
                    isFavour = it.favour ?: false,
                    isFavourite = {
                        viewModel.updateFood(it.id!!, it.favour ?: false)
                    }
                )
            }
        }
        if(state.isEmpty()) {
            Text(
                text = "you don't save anything",
                color = Color.Black
            )
        }
    }
    when(val response = viewModel.updateResponse) {
        is Response.Loading ->
            CustomProgressBar()
        is Response.Success ->
            LaunchedEffect(response.data) {
                if(response.data)
                    Utils.showMessage(context, "update successful")
            }
        is Response.Failure ->
            LaunchedEffect(response.e) {
                Utils.showMessage(context, "update successful")
            }
    }
}