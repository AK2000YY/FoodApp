package com.example.foodapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodapp.core.constant.Routes.REGISTRATION_ROUTE
import com.example.foodapp.navigation.graph.registration
import com.example.foodapp.navigation.Screen.ProfileScreen
import com.example.foodapp.navigation.Screen.AppScreen
import com.example.foodapp.presentation.cameraPreview.CameraPreviewScreen
import com.example.foodapp.presentation.myApp.MyAppScreen

@Composable
fun NavSetup(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = REGISTRATION_ROUTE
    ) {
        registration(
            modifier = modifier.fillMaxSize(),
            navHostController = navHostController
        )

        composable(
            route = AppScreen.route
        ) {
            MyAppScreen(
                modifier = modifier.fillMaxSize(),
                toCamera = {
                    navHostController.navigate(Screen.CameraViewScreen.route)
                }
            )
        }

        composable(
            route = Screen.CameraViewScreen.route
        ) {
            CameraPreviewScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        }

        composable(
            route = ProfileScreen.route
        ) {

        }
    }
}