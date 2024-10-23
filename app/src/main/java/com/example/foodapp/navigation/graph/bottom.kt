package com.example.foodapp.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodapp.core.constant.Routes.APP_ROUTE
import com.example.foodapp.core.constant.Routes.FIND_OUT_ROUTE
import com.example.foodapp.navigation.Screen.DiscoveredFoodScreen
import com.example.foodapp.navigation.Screen.FavouriteFoodScreen
import com.example.foodapp.presentation.discoveredFood.DiscoveredFoodScreen
import com.example.foodapp.presentation.favouriteFood.FavouriteFoodScreen

fun NavGraphBuilder.bottom(
    modifier: Modifier,
    navHostController: NavHostController,
) {
    navigation(
        route = APP_ROUTE,
        startDestination = FIND_OUT_ROUTE
    ) {

        findOutFood(
            modifier = modifier,
            navHostController = navHostController
        )

        composable(
            route = DiscoveredFoodScreen.route
        ) {
            DiscoveredFoodScreen(
                modifier = modifier
            )
        }

        composable(
            route = FavouriteFoodScreen.route
        ) {
            FavouriteFoodScreen(
                modifier = modifier
            )
        }

    }
}