package com.cleverlycode.littlelemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cleverlycode.littlelemon.screens.Home
import com.cleverlycode.littlelemon.screens.Onboarding
import com.cleverlycode.littlelemon.screens.Profile

@Composable
fun Navigation(navController: NavHostController) {
    val isLoggedIn = PreferencesManager(context = LocalContext.current).getData("firstName", "").isNotEmpty()
    NavHost(navController = navController, startDestination = if (isLoggedIn) "Home" else "Onboarding") {
        composable(Home.route) {
            Home(navController = navController)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }
        composable(Onboarding.route){
            Onboarding(navController = navController)
        }
    }
}