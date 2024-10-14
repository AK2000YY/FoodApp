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
            modifier = Modifier.fillMaxSize(),
            navHostController = navHostController
        )

        composable(
            route = AppScreen.route
        ) {  }

        composable(
            route = ProfileScreen.route
        ) {

        }
    }
}