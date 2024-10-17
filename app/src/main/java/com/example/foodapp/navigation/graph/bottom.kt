package com.example.foodapp.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodapp.core.constant.Routes.APP_ROUTE
import com.example.foodapp.navigation.Screen.FindOutKindFoodScreen
import com.example.foodapp.navigation.Screen.DiscoveredFoodScreen
import com.example.foodapp.navigation.Screen.FavouriteFoodScreen

fun NavGraphBuilder.bottom(
    navHostController: NavHostController
) {
    navigation(
        route = APP_ROUTE,
        startDestination = FindOutKindFoodScreen.route
    ) {
        composable(
            route = FindOutKindFoodScreen.route
        ) {
        }

        composable(
            route = DiscoveredFoodScreen.route
        ) {
        }

        composable(
            route = FavouriteFoodScreen.route
        ) {
        }
    }
}