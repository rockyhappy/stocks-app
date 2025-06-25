package com.devrachit.groww.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.devrachit.groww.utility.constants.Constants.Companion.MAIN_ANIMATION_DURATION

fun NavGraphBuilder.mainAnimatedComposable(
    screen: Screen,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = screen.route,
        arguments = arguments,
        enterTransition = { mainSlideEnterTransition(this) },
        exitTransition = { mainSlideExitTransition(this) },
        popEnterTransition = { mainSlidePopEnterTransition() },
        popExitTransition = { mainSlidePopExitTransition() }
    ) { backStackEntry ->
        content(backStackEntry)
    }
}

/**
 * Creates a slide-in enter transition for main navigation
 */
private fun mainSlideEnterTransition(
    scope: AnimatedContentTransitionScope<NavBackStackEntry>
): EnterTransition {
    return scope.slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(MAIN_ANIMATION_DURATION)
    )
}

/**
 * Creates a slide-out exit transition for main navigation
 */
private fun mainSlideExitTransition(
    scope: AnimatedContentTransitionScope<NavBackStackEntry>
): ExitTransition {
    return scope.slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(MAIN_ANIMATION_DURATION)
    )
}

/**
 * Creates a slide-in transition for popping the back stack in main navigation
 */
private fun AnimatedContentTransitionScope<NavBackStackEntry>.mainSlidePopEnterTransition(): EnterTransition {
    return slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(MAIN_ANIMATION_DURATION)
    )
}

/**
 * Creates a slide-out transition for popping the back stack in main navigation
 */
private fun AnimatedContentTransitionScope<NavBackStackEntry>.mainSlidePopExitTransition(): ExitTransition {
    return slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(MAIN_ANIMATION_DURATION)
    )
}