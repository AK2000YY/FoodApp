package com.example.foodapp.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.foodapp.R

@Composable
fun AppTitle(
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.displayMedium
) {
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier.size(30.dp).align(Alignment.End),
            painter = painterResource(id = R.drawable.leaves_white),
            contentDescription = null
        )
        Text(
            text = "Discover Food  ",
            style = style,
            color = Color.White, fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.ExtraBold
        )
    }
}