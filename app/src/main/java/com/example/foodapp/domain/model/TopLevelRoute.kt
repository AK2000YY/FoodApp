package com.example.foodapp.domain.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.foodapp.navigation.Screen

data class TopLevelRoute(
    val name: String,
    val screen: Screen,
    val icon: ImageVector
)
