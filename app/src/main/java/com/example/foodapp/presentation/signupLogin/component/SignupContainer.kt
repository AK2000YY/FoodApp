package com.example.foodapp.presentation.signupLogin.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodapp.core.component.CustomTextField
import com.example.foodapp.ui.theme.FirstBackground


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
                    onChange = emailChange
                )
                CustomTextField(
                    text = password,
                    onChange = passwordChange
                )
                CustomTextField(
                    text = confirmPassword,
                    onChange = confirmPasswordChange
                )
            }
        }
    }
}
