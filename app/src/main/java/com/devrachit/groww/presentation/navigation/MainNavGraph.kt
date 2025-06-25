package com.devrachit.groww.presentation.navigation

import androidx.navigation.NavHost
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devrachit.groww.presentation.screens.bottomBar.BottomBarScreen
import com.devrachit.groww.presentation.screens.bottomBar.BottomBarScreenViewmodel
import com.devrachit.groww.presentation.screens.details.DetailsScreen
import com.devrachit.groww.presentation.screens.details.DetailsScreenViewmodel
import com.devrachit.groww.presentation.screens.displayList.DisplayListScreen
import com.devrachit.groww.presentation.screens.displayList.DisplayScreenViewmodel
import com.devrachit.groww.utility.constants.Constants.Companion.START_DESTINATION

@Composable
fun MainNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = START_DESTINATION
    )
    {
        mainAnimatedComposable(screen = Screen.BottomBarScreen) {
            val viewmodel = hiltViewModel<BottomBarScreenViewmodel>()
            val uiState = viewmodel.uiState.collectAsStateWithLifecycle()
            BottomBarScreen(
                title = uiState.value.title,
                navigateToDetailsScreen = {
                    navController.navigate(Screen.DetailsScreen.route)
                },
                navigateToDisplayScreen = {
                    navController.navigate(Screen.DisplayScreen.route)
                }
            )
        }
        mainAnimatedComposable(screen = Screen.DetailsScreen) {
            val viewmodel = hiltViewModel<DetailsScreenViewmodel>()
            val uiState = viewmodel.uiState.collectAsStateWithLifecycle()
            DetailsScreen(
                title = uiState.value.title,
            )
        }
        mainAnimatedComposable(screen = Screen.DisplayScreen) {
            val viewmodel = hiltViewModel<DisplayScreenViewmodel>()
            val uiState = viewmodel.uiState.collectAsStateWithLifecycle()
            DisplayListScreen(
                title = uiState.value.title,
                navigateToDetailsScreen = {
                    navController.navigate(Screen.DetailsScreen.route)
                }
            )
        }
    }

}