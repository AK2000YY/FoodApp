package com.example.foodapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodapp.core.constant.Routes.REGISTRATION_ROUTE
import com.example.foodapp.navigation.graph.registration
import com.example.foodapp.navigation.Screen.ProfileScreen

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
            navHostController = navHostController
        )
        composable(
            route = ProfileScreen.route
        ) {

        }
    }
}