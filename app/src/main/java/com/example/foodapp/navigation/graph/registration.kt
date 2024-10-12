package com.example.foodapp.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodapp.core.constant.Routes
import com.example.foodapp.navigation.Screen.IntroducedScreen
import com.example.foodapp.navigation.Screen.SignupScreen
import com.example.foodapp.navigation.Screen.LoginScreen
import com.example.foodapp.presentation.introduction.IntroductionScreen


fun NavGraphBuilder.registration(
    modifier: Modifier,
    navHostController: NavHostController
) {
    navigation(
        route = Routes.REGISTRATION_ROUTE,
        startDestination = IntroducedScreen.route
    ) {

        composable(
            route = IntroducedScreen.route
        ) {
            IntroductionScreen(
                modifier = modifier
            )
        }

        composable(
            route = SignupScreen.route
        ) {

        }

        composable(
            route = LoginScreen.route
        ) {

        }
    }
}