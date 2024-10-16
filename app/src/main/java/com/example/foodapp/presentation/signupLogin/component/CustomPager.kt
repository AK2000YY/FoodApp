package com.example.foodapp.presentation.signupLogin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodapp.ui.theme.FirstBackground
import com.example.foodapp.ui.theme.SecondBackground
import com.example.foodapp.ui.theme.White
import kotlinx.coroutines.launch

@Composable
fun CustomPager(
    modifier: Modifier = Modifier,
    emailLogin: String,
    passwordLogin: String,
    emailSignup: String,
    passwordSignup: String,
    confirmPasswordSignup: String,
    emailLoginChange: (String) -> Unit,
    passwordLoginChange: (String) -> Unit,
    emailSignupChange: (String) -> Unit,
    passwordSignupChange: (String) -> Unit,
    confirmPasswordSignupChange:(String) -> Unit,
    login: () -> Unit,
    signup: () -> Unit
) {
    val pagerState = rememberPagerState( pageCount =  { 2 } )
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(
                modifier = Modifier
                    .clickable {
                        coroutineScope.launch {
                            pagerState.scrollToPage(0)
                        }
                    }
                    .align(Alignment.Bottom),
                text = "Login",
                isColor = pagerState.currentPage == 0
            )
            CustomText(
                modifier = Modifier
                    .clickable {
                        coroutineScope.launch {
                            pagerState.scrollToPage(1)
                        }
                    }
                    .align(Alignment.Bottom),
                text = "Signup",
                isColor = pagerState.currentPage == 1
            )
        }
        HorizontalPager(
            modifier = Modifier
                .weight(3f),
            state = pagerState
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if(page == 0)
                    LoginContainer(
                        modifier = Modifier
                            .fillMaxSize(),
                        email = emailLogin,
                        password = passwordLogin,
                        emailChange = emailLoginChange,
                        passwordChange = passwordLoginChange
                    )
                else
                    SignupContainer(
                        modifier = Modifier
                            .fillMaxSize(),
                        email = emailSignup,
                        password = passwordSignup,
                        confirmPassword = confirmPasswordSignup,
                        emailChange = emailSignupChange,
                        passwordChange = passwordSignupChange,
                        confirmPasswordChange = confirmPasswordSignupChange,
                    )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 0.dp, bottom = 20.dp, start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = FirstBackground
            ),
            onClick = if(pagerState.currentPage == 0) login else signup
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 5.dp),
                text = if(pagerState.currentPage == 0) "Login" else "Signup",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }

    }

}

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    isColor: Boolean
) {
    Text(
        modifier = modifier
            .clip(RoundedCornerShape(25.dp))
            .background(if (isColor) FirstBackground else White)
            .padding(horizontal = 50.dp, vertical = 5.dp),
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = if(isColor) Color.White else SecondBackground
    )
}