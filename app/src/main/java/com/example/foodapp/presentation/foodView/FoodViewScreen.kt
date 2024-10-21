package com.example.foodapp.presentation.foodView

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodapp.navigation.sharedData.SharedViewModel

@Composable
fun FoodViewScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth(),
            bitmap = state.image!!.asImageBitmap(),
            contentDescription = null
        )
        Text(text = state.name!!)
    }
}