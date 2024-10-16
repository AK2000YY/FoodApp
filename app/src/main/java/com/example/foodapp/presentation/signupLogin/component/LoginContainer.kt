package com.example.foodapp.presentation.signupLogin.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.core.component.CustomTextField

@Composable
fun LoginContainer(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    emailChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
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
            }
        }
    }
}


@Preview
@Composable
private fun LoginContainerPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LoginContainer(
            Modifier
                .height(500.dp)
                .width(400.dp),
            email = "", password = "", emailChange = {}, passwordChange = {})
    }
}