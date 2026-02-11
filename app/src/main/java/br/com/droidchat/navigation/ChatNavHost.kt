package br.com.droidchat.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import br.com.droidchat.navigation.ChatDestinations.SignInDestination
import br.com.droidchat.navigation.ChatDestinations.SignOutDestination
import br.com.droidchat.navigation.ChatDestinations.SplashDestination
import br.com.droidchat.ui.feature.signin.SignInRoute
import br.com.droidchat.ui.feature.splash.SplashRoute

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashDestination
    ) {
        composable<SplashDestination> {
            SplashRoute {
                navController.navigate(
                    route = SignInDestination,
                    navOptions = navOptions {
                        popUpTo(SplashDestination) { inclusive = true }
                    }
                )
            }
        }

        composable<SignInDestination> {
            SignInRoute()
        }

        composable<SignOutDestination> {

        }
    }
}