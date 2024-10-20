package com.example.foodapp.presentation.myApp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.domain.model.TopLevelRoute
import com.example.foodapp.navigation.BottomNavSetup
import com.example.foodapp.navigation.Screen

val topLevelRoutes = listOf(
    TopLevelRoute("Favourite", Screen.FavouriteFoodScreen, Icons.Rounded.FavoriteBorder),
    TopLevelRoute("Discovered", Screen.DiscoveredFoodScreen, Icons.Rounded.Search),
    TopLevelRoute("Find Out", Screen.FindOutKindFoodScreen, Icons.Rounded.Add),
)

@Composable
fun MyAppScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        containerColor = Color.White,
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination?.route

            // Show bottom navigation only for specific screens
            if (currentDestination == Screen.FavouriteFoodScreen.route ||
                currentDestination == Screen.DiscoveredFoodScreen.route ||
                currentDestination == Screen.FindOutKindFoodScreen.route
            ) {
                BottomNavigation(
                    contentColor = Color.White,
                    backgroundColor = Color.White,
                    elevation = 0.dp
                ) {
                    topLevelRoutes.forEach { screen ->
                        BottomNavigationItem(
                            icon = { Icon(screen.icon, contentDescription = screen.name) },
                            label = { Text(screen.name) },
                            selected = currentDestination == screen.screen.route,
                            onClick = {
                                navController.navigate(screen.screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        BottomNavSetup(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            navHostController = navController
        )
    }
}