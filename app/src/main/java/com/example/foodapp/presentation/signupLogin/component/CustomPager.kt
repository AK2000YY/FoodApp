package com.example.foodapp.presentation.signupLogin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.ui.theme.FirstBackground
import com.example.foodapp.ui.theme.SecondBackground

@Composable
fun CustomPager(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState( pageCount =  { 2 } )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(text = "Login", isColor = true)
            CustomText(text = "Signup", isColor = false)
        }
        LoginContainer(email = "", password = "", emailChange = {}, passwordChange = {}) {
            
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
            .background(if (isColor) FirstBackground else Color.White)
            .padding(horizontal = 50.dp, vertical = 10.dp),
        text = text,
        style = MaterialTheme.typography.titleMedium,
        color = if(isColor) Color.White else SecondBackground
    )
}

@Preview
@Composable
private fun CustomPagerPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CustomPager(
            modifier = Modifier
                .height(500.dp)
                .width(300.dp)
        )
    }
}