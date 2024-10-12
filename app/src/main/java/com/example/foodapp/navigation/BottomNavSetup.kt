package com.example.foodapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.foodapp.core.constant.Routes.APP_ROUTE
import com.example.foodapp.navigation.graph.bottom

@Composable
fun BottomNavSetup(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = APP_ROUTE
    ) {
        bottom(
            navHostController = navHostController
        )
    }
}