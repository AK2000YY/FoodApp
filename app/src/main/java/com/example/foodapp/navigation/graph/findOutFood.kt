package com.example.foodapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
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
import com.example.foodapp.presentation.foodView.FoodViewScreen

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
                viewModel = it.sharedViewModel(navHostController = navHostController),
                toCamera = {
                    navHostController.navigate(CameraViewScreen.route)
                },
                toFoodView = {
                    navHostController.navigate(FoodKindView.route)
                }
            )
        }

        composable(
            route = CameraViewScreen.route
        ) {
            CameraPreviewScreen(
                modifier = modifier,
                viewModel = it.sharedViewModel(navHostController = navHostController),
                toFoodView = {
                    navHostController.navigate(FoodKindView.route)
                }
            )
        }

        composable(
            route = FoodKindView.route,
        ) {
            FoodViewScreen(
                modifier = modifier,
                viewModel = it.sharedViewModel(navHostController = navHostController)
            )
        }
    }
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(
    navHostController: NavHostController
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navHostController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}