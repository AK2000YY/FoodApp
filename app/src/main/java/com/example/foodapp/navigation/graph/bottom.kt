package com.example.foodapp.navigation.graph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodapp.core.constant.Routes.APP_ROUTE
import com.example.foodapp.navigation.Screen.FindOutKindFoodScreen
import com.example.foodapp.navigation.Screen.DiscoveredFoodScreen
import com.example.foodapp.navigation.Screen.FavouriteFoodScreen
import com.example.foodapp.presentation.findOutFood.FindOutFoodScreen

fun NavGraphBuilder.bottom(
    modifier: Modifier,
    navHostController: NavHostController,
    toCamera: () -> Unit
) {
    navigation(
        route = APP_ROUTE,
        startDestination = FindOutKindFoodScreen.route
    ) {
        composable(
            route = FindOutKindFoodScreen.route
        ) {
            FindOutFoodScreen(
                modifier = modifier,
                toCamera = toCamera
            )
        }

        composable(
            route = DiscoveredFoodScreen.route
        ) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Discovered")
            }
        }

        composable(
            route = FavouriteFoodScreen.route
        ) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Favourite")
            }
        }
    }
}