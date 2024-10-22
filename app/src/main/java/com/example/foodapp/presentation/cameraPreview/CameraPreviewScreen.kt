package com.example.foodapp.presentation.cameraPreview

import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PhotoCamera
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.foodapp.navigation.sharedData.SharedViewModel

@Composable
fun CameraPreviewScreen(
    modifier: Modifier = Modifier,
    viewModel: SharedViewModel = hiltViewModel(),
    toFoodView: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    Box(
        modifier = modifier
    ) {
        val lifecycleOwner = LocalLifecycleOwner.current
        AndroidView(
            factory = {
                PreviewView(it).apply {
                    this.controller = viewModel.controller
                    viewModel.controller.bindToLifecycle(lifecycleOwner)
                }
            },
            modifier = Modifier
                .fillMaxSize()
        )
        if (state.name != null) {
            Text(
                text = state.name!!,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Icon(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp)
                    .clickable {
                        viewModel.captureImage(
                            toFoodView = toFoodView
                        )
                    },
                imageVector = Icons.Rounded.PhotoCamera,
                contentDescription =null,
                tint = Color.White
            )
        }
    }
}