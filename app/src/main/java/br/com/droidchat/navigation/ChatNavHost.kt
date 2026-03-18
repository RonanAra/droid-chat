package br.com.droidchat.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import br.com.droidchat.navigation.ChatDestinations.SignInDestination
import br.com.droidchat.navigation.ChatDestinations.SignUpDestination
import br.com.droidchat.navigation.ChatDestinations.SplashDestination
import br.com.droidchat.navigation.extension.navSlideIn
import br.com.droidchat.navigation.extension.navSlideOut
import br.com.droidchat.ui.feature.signin.SignInRoute
import br.com.droidchat.ui.feature.signup.SignUpRoute
import br.com.droidchat.ui.feature.splash.SplashRoute

@Composable
fun ChatNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = SplashDestination
    ) {
        composable<SplashDestination>{
            SplashRoute {
                navController.navigate(
                    route = SignInDestination,
                    navOptions = navOptions {
                        popUpTo(SplashDestination) { inclusive = true }
                    }
                )
            }
        }

        composable<SignInDestination>(
            enterTransition = { navSlideIn() },
            exitTransition = { navSlideOut() }
        )  {
            SignInRoute(navigateToSignUp = {
                navController.navigate(SignUpDestination)
            })
        }

        composable<SignUpDestination>(
            enterTransition = { navSlideIn() },
            exitTransition = { navSlideOut() }
        )  {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SignUpRoute()
            }
        }
    }
}

