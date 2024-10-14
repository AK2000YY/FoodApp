package com.example.foodapp.presentation.introduction

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.ui.theme.FirstBackground
import com.example.foodapp.ui.theme.SecondBackground

@Composable
fun IntroductionScreen(modifier: Modifier = Modifier) {
    val brush = Brush.linearGradient(
        listOf(FirstBackground, SecondBackground)
    )
    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(brush),
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Image(
                    modifier = Modifier.size(30.dp).align(Alignment.End),
                    painter = painterResource(id = R.drawable.leaves_white),
                    contentDescription = null
                )
                Text(
                    text = "Discover Food  ",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White, fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
            ) {
                Box(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier.weight(2f),
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun PrevScreen() {
    IntroductionScreen()
}