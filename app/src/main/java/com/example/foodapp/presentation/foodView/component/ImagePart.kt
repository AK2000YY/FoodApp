package com.example.foodapp.presentation.foodView.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun ImagePart(
    modifier: Modifier = Modifier,
    bitmap: Bitmap,
    onSave: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Row (
            modifier = Modifier
                .padding(end = 20.dp, top = 20.dp)
                .align(Alignment.TopEnd)
        ){
            CustomButton(
                text = "Save",
                onClick = {
                    onSave()
                }
            )
        }
    }
}