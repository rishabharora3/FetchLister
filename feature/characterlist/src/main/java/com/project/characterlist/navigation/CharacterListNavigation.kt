package com.project.characterlist.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.project.characterlist.CharacterListRoute

const val characterListRoute = "character_list_route"

fun NavController.navigateToCharacterList(navOptions: NavOptions? = null) {
    this.navigate(characterListRoute, navOptions)
}

@SuppressLint("VisibleForTests")
fun NavGraphBuilder.characterListScreen(
    onCharacterListItemClick: (String) -> Unit,
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(route = characterListRoute) {
        CharacterListRoute(onCharacterListItemClick)
    }
}
