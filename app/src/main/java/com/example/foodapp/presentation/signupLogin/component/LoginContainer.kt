package com.example.foodapp.presentation.signupLogin.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.component.CustomTextField
import com.example.foodapp.ui.theme.FirstBackground

@Composable
fun LoginContainer(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    emailChange: (String) -> Unit,
    passwordChange: (String) -> Unit,
    login: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CustomTextField(
            text = email,
            onChange = emailChange
        )
        CustomTextField(
            text = password,
            onChange = passwordChange
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = FirstBackground
            ),
            onClick = login
        ) {
            Text(
                modifier = Modifier
                    .padding(vertical = 5.dp),
                text = "Login",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
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
        LoginContainer(email = "", password = "", emailChange = {}, passwordChange = {}) {

        }
    }
}