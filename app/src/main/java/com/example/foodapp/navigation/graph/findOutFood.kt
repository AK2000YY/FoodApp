package com.example.foodapp.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodapp.core.constant.Routes.FIND_OUT_ROUTE
import com.example.foodapp.navigation.Screen.FindOutKindFoodScreen
import com.example.foodapp.navigation.Screen.CameraViewScreen
import com.example.foodapp.navigation.Screen.FoodKindView
import com.example.foodapp.presentation.cameraPreview.CameraPreviewScreen
import com.example.foodapp.presentation.findOutFood.FindOutFoodScreen

fun NavGraphBuilder.findOutFood(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    navigation(
        route = FIND_OUT_ROUTE,
        startDestination = FindOutKindFoodScreen.route
    ) {
        composable(
            route = FindOutKindFoodScreen.route
        ) {
            FindOutFoodScreen(
                modifier = modifier,
                toCamera = {
                    navHostController.navigate(CameraViewScreen.route)
                }
            )
        }

        composable(
            route = CameraViewScreen.route
        ) {
            CameraPreviewScreen(
                modifier = modifier
            )
        }

        composable(
            route = FoodKindView.route
        ) {}
    }
}