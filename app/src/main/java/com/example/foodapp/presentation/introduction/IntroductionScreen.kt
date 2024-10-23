package com.example.foodapp.presentation.introduction

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodapp.core.constant.Constant.USER_NOT_FOUND
import com.example.foodapp.domain.model.Response
import com.example.foodapp.presentation.introduction.component.IntroductionContainer

@Composable
fun IntroductionScreen(
    modifier: Modifier = Modifier,
    viewModel: IntroductionViewModel = hiltViewModel(),
    toRegister: () -> Unit,
    toVerification: () -> Unit,
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
                if(response.e.message == USER_NOT_FOUND) toRegister()
                else toVerification()
            }
    }
}