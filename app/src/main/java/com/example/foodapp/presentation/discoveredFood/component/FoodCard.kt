package com.example.foodapp.presentation.discoveredFood.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.foodapp.ui.theme.SecondBackground

@Composable
fun FoodCard(
    modifier: Modifier = Modifier,
    image: String,
    text: String,
    date: String,
    isFavour: Boolean,
    isFavourite: () -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(SecondBackground),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = image,
            modifier = Modifier
                .padding(start = 8.dp, end = 18.dp)
                .height(100.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(50.dp)),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = date,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
        }
        Icon(
            modifier = Modifier
                .padding(end = 20.dp)
                .align(Alignment.CenterVertically)
                .clickable { isFavourite() },
            imageVector =
                if(isFavour) Icons.Rounded.Favorite
                else Icons.Rounded.FavoriteBorder,
            contentDescription = null,
            tint = Color.White
        )
    }
}