package com.example.foodapp.navigation

import com.example.foodapp.core.constant.Routes.INTRODUCTORY_SCREEN
import com.example.foodapp.core.constant.Routes.SIGNUP_SCREEN
import com.example.foodapp.core.constant.Routes.LOGIN_SCREEN
import com.example.foodapp.core.constant.Routes.FIND_OUT_KIND_FOOD_SCREEN
import com.example.foodapp.core.constant.Routes.DISCOVERED_FOOD_SCREEN
import com.example.foodapp.core.constant.Routes.FAVOURITE_FOOD_SCREEN
import com.example.foodapp.core.constant.Routes.PROFILE_SCREEN


sealed class Screen(val route: String) {
    data object IntroducedScreen: Screen(INTRODUCTORY_SCREEN)
    data object SignupScreen: Screen(SIGNUP_SCREEN)
    data object LoginScreen: Screen(LOGIN_SCREEN)
    data object FindOutKindFoodScreen: Screen(FIND_OUT_KIND_FOOD_SCREEN)
    data object DiscoveredFoodScreen: Screen(DISCOVERED_FOOD_SCREEN)
    data object FavouriteFoodScreen: Screen(FAVOURITE_FOOD_SCREEN)
    data object ProfileScreen: Screen(PROFILE_SCREEN)
}