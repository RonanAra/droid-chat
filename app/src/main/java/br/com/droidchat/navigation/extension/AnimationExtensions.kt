package br.com.droidchat.navigation.extension

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.navSlideIn(
    direction: SlideDirection = SlideDirection.Right,
    durationMillis: Int = 300
): EnterTransition = slideIntoContainer(
    towards = direction,
    animationSpec = tween(durationMillis = durationMillis)
)

fun AnimatedContentTransitionScope<NavBackStackEntry>.navSlideOut(
    direction: SlideDirection = SlideDirection.Left,
    durationMillis: Int = 300
): ExitTransition = slideOutOfContainer(
    towards = direction,
    animationSpec = tween(durationMillis = durationMillis)
)
