package com.project.fetchlister.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.project.characterdetail.navigation.characterDetailScreen
import com.project.characterdetail.navigation.navigateToCharacterDetail
import com.project.characterlist.navigation.characterListRoute
import com.project.characterlist.navigation.characterListScreen
import com.project.fetchlister.ui.FetchListerAppState

/**
 * Top-level navigation graph. Navigation is organized as explained at
 * https://d.android.com/jetpack/compose/nav-adaptive
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun FetchListerNavHost(
    appState: FetchListerAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = characterListRoute,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        characterListScreen(
            onCharacterListItemClick = navController::navigateToCharacterDetail,
            onShowSnackbar = onShowSnackbar,
        )

        characterDetailScreen(
            onShowSnackbar = onShowSnackbar,
        )
    }

}