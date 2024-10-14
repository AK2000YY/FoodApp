package com.example.foodapp.presentation.introduction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodapp.domain.model.Response
import com.example.foodapp.presentation.introduction.component.IntroductionContainer

@Composable
fun IntroductionScreen(
    modifier: Modifier = Modifier,
    viewModel: IntroductionViewModel = hiltViewModel(),
    toRegister: () -> Unit,
    toApp: () -> Unit
) {
    when(val response = viewModel.response) {
        is Response.Loading ->
            IntroductionContainer(
                modifier = modifier
            )
        is Response.Success ->
            LaunchedEffect(response.data) {
                if(response.data)
                    toApp()
            }
        is Response.Failure ->
            LaunchedEffect(response.e) {
                toRegister()
            }
    }
}

@Preview
@Composable
private fun PrevScreen() {
}