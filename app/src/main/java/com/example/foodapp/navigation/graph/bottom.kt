package com.example.foodapp.navigation.graph

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodapp.core.constant.Routes.APP_ROUTE
import com.example.foodapp.core.constant.Routes.FIND_OUT_ROUTE
import com.example.foodapp.navigation.Screen.DiscoveredFoodScreen
import com.example.foodapp.navigation.Screen.FavouriteFoodScreen

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