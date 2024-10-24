package com.example.foodapp.presentation.signupLogin.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.foodapp.core.component.CustomTextField


@Composable
fun SignupContainer(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    confirmPassword: String,
    emailChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    confirmPasswordChange: (String) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
            ) {
                CustomTextField(
                    text = email,
                    placeholder = "Enter Your Email",
                    onChange = emailChange
                )
                CustomTextField(
                    text = password,
                    placeholder = "Enter Your Password",
                    onChange = passwordChange
                )
                CustomTextField(
                    text = confirmPassword,
                    placeholder = "Enter Your confirm Password",
                    onChange = confirmPasswordChange
                )
            }
        }
    }
}
