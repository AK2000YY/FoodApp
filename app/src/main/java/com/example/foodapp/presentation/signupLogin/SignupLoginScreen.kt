package com.example.foodapp.presentation.signupLogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodapp.R
import com.example.foodapp.core.classes.Utils
import com.example.foodapp.core.component.AppTitle
import com.example.foodapp.core.component.CustomProgressBar
import com.example.foodapp.domain.model.Response
import com.example.foodapp.presentation.signupLogin.component.CustomPager
import com.example.foodapp.ui.theme.DarkWhite
import com.example.foodapp.ui.theme.FirstBackground
import com.example.foodapp.ui.theme.SecondBackground
import com.example.foodapp.ui.theme.White

@Composable
fun SignupLoginScreen(
    modifier: Modifier = Modifier,
    viewModel: SignupLoginViewModel = hiltViewModel(),
    toMyApp: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        val brush = Brush.linearGradient(
            listOf(FirstBackground.copy(0.7f), SecondBackground.copy(0.7f))
        )
        val height = LocalConfiguration.current.screenHeightDp
        val width = LocalConfiguration.current.screenWidthDp
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkWhite)
                .padding(innerPadding)
        ) {
            Image(
                modifier = Modifier
                    .height((height / 3).dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp))
                    .align(Alignment.TopCenter),
                painter = painterResource(id = R.drawable.register),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((height / 3).dp)
                    .clip(RoundedCornerShape(bottomEnd = 100.dp, bottomStart = 100.dp))
                    .background(brush)
            ) {
                AppTitle(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 20.dp),
                    style = MaterialTheme.typography.displaySmall
                )
            }
            Box(
                modifier = Modifier
                    .width(width.dp - 80.dp)
                    .height(height.dp - 300.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(White)
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                CustomPager(
                    emailLogin = viewModel.loginEmail,
                    passwordLogin = viewModel.loginPassword,
                    emailSignup = viewModel.signupEmail,
                    passwordSignup = viewModel.signupPassword,
                    confirmPasswordSignup = viewModel.signupConfirmPassword,
                    emailLoginChange = { viewModel.loginEmail = it },
                    passwordLoginChange = { viewModel.loginPassword = it },
                    emailSignupChange = { viewModel.signupEmail = it },
                    passwordSignupChange = { viewModel.signupPassword = it },
                    confirmPasswordSignupChange = { viewModel.signupConfirmPassword = it },
                    login = { viewModel.login() },
                    signup = { viewModel.signup() }
                )
            }
        }
        when(val response = viewModel.response) {
            is Response.Loading ->
                CustomProgressBar()
            is Response.Success ->
                LaunchedEffect(key1 = response.data) {
                    if(response.data)
                        toMyApp()
                }
            is Response.Failure -> {
                LaunchedEffect(response.e) {
                    Utils.showMessage(context, response.e.message)
                }
            }
        }
    }
}
