package com.example.foodapp.presentation.findOutFood

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodapp.R
import com.example.foodapp.presentation.findOutFood.component.CustomButton

@Composable
fun FindOutFoodScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .padding(top = 50.dp)
                .height(200.dp)
                .align(Alignment.TopStart),
            painter = painterResource(id = R.drawable.secondpicture),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .padding(bottom = 30.dp)
                .height(250.dp)
                .align(Alignment.BottomEnd),
            painter = painterResource(id = R.drawable.halfpicture),
            contentDescription = null
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CustomButton(
                modifier = Modifier
                    .height(60.dp)
                    .width(220.dp),
                text = "Take Photo From Camera",
                onClick = {}
            )
            CustomButton(
                modifier = Modifier
                    .height(60.dp)
                    .width(220.dp),
                text = "Take Photo From Gallery",
                onClick = {}
            )
        }
    }
}

