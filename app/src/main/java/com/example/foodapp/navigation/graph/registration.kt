package com.example.foodapp.navigation.graph

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.foodapp.core.constant.Routes
import com.example.foodapp.navigation.Screen.IntroducedScreen
import com.example.foodapp.navigation.Screen.SignupLoginScreen
import com.example.foodapp.navigation.Screen.AppScreen
import com.example.foodapp.navigation.Screen.VerificationScreen
import com.example.foodapp.presentation.introduction.IntroductionScreen
import com.example.foodapp.presentation.signupLogin.SignupLoginScreen
import com.example.foodapp.presentation.verification.VerificationScreen

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
                modifier = modifier,
                toRegister = {
                    navHostController.navigate(SignupLoginScreen.route) {
                        popUpTo(navHostController.graph.startDestinationId){
                            inclusive = true
                        }
                    }
                },
                toVerification = {
                    navHostController.navigate(VerificationScreen.route) {
                        popUpTo(navHostController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
                toApp =  {
                    navHostController.navigate(AppScreen.route) {
                        popUpTo(navHostController.graph.startDestinationId){
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = SignupLoginScreen.route
        ) {
            SignupLoginScreen(
                modifier = modifier,
                toVerify = {
                    navHostController.navigate(VerificationScreen.route) {
                        popUpTo(navHostController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = VerificationScreen.route
        ) {
            VerificationScreen(
                modifier = modifier,
                toMyApp = {
                    navHostController.navigate(AppScreen.route) {
                        popUpTo(navHostController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                }
            )
        }

    }
}