package com.example.foodapp.presentation.verification.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.ui.theme.FirstBackground
import com.example.foodapp.ui.theme.SecondBackground

@Composable
fun VerificationView(
    modifier: Modifier = Modifier,
    verifyEmail: () -> Unit
) {
    val brush = Brush.linearGradient(
        listOf(FirstBackground, SecondBackground)
    )
    Box(
        modifier = modifier
            .background(brush),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp),
                painter = painterResource(id = R.drawable.tick),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "check your email to verify account",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp)
        ) {
            Text(
                text = "if you don't receive email ",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .clickable {
                        verifyEmail()
                    },
                text = "press here",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Cyan
            )
        }
    }
}
