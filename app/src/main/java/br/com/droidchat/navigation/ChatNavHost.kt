package br.com.droidchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.droidchat.ui.feature.splash.SplashRoute

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable(route = "splash") {
            SplashRoute()
        }
    }
}