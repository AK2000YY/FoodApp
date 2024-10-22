package com.example.foodapp.presentation.foodView.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodapp.ui.theme.SecondBackground

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box (
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(SecondBackground)
            .padding(horizontal = 30.dp, vertical = 8.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White
        )
    }
}

