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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodapp.presentation.discoveredFood.component.FoodCard

@Composable
fun DiscoveredFoodScreen(
    modifier: Modifier = Modifier,
    viewModel: DiscoveredFoodViewModel = hiltViewModel()
) {
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
                    text = it!!.name!!,
                    date = it.date!!
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
}