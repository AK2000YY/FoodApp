package com.example.foodapp.presentation.verification

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodapp.core.classes.Utils
import com.example.foodapp.domain.model.Response
import com.example.foodapp.presentation.verification.component.VerificationView

@Composable
fun VerificationScreen(
    modifier: Modifier = Modifier,
    viewModel: VerificationViewModel = hiltViewModel(),
    toMyApp: () -> Unit
) {
    val context = LocalContext.current
    when(val response = viewModel.response) {
            is Response.Loading ->
                VerificationView(
                    modifier = modifier,
                    verifyEmail = { viewModel.sendEmail() }
                )
            is Response.Success ->
                LaunchedEffect(response.data) {
                    if(response.data)
                        toMyApp()
                }
            is Response.Failure -> {
                VerificationView(
                    modifier = modifier,
                    verifyEmail = { viewModel.sendEmail() }
                )
                LaunchedEffect(response.e) {
                    Utils.showMessage(context, response.e.message)
                }
            }
         }
}