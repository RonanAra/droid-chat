package br.com.droidchat.navigation

import kotlinx.serialization.Serializable

sealed interface ChatDestinations {

    @Serializable
    data object SplashDestination : ChatDestinations

    @Serializable
    data object SignInDestination : ChatDestinations

    @Serializable
    data object SignUpDestination : ChatDestinations
}
