package com.example.foodapp.presentation.foodView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodapp.core.classes.Utils
import com.example.foodapp.core.component.CustomProgressBar
import com.example.foodapp.domain.model.Response
import com.example.foodapp.navigation.sharedData.SharedViewModel
import com.example.foodapp.presentation.foodView.component.ImagePart

@Composable
fun FoodViewScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = hiltViewModel()
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
        Text(
            modifier = Modifier
                .padding(top = 20.dp, start = 8.dp),
            text = "Name : ${state.name}",
            style = MaterialTheme.typography.bodyLarge
        )
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