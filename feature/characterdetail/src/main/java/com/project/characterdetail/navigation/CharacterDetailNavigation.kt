package com.project.characterdetail.navigation

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.project.characterdetail.CharacterDetailRoute
import java.net.URLDecoder
import java.net.URLEncoder
import kotlin.text.Charsets.UTF_8

private val URL_CHARACTER_ENCODING = UTF_8.name()

@VisibleForTesting
internal const val characterIdArg = "characterId"

internal class CharacterDetailArgs(val characterId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(
                URLDecoder.decode(
                    checkNotNull(savedStateHandle[characterIdArg]),
                    URL_CHARACTER_ENCODING
                )
            )
}

fun NavController.navigateToCharacterDetail(characterId: String) {
    val encodedId = URLEncoder.encode(characterId, URL_CHARACTER_ENCODING)
    this.navigate("character_detail_route/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.characterDetailScreen(
    onShowSnackbar: suspend (String, String?) -> Boolean,
) {
    composable(
        route = "character_detail_route/{$characterIdArg}",
        arguments = listOf(
            navArgument(characterIdArg) { type = NavType.StringType },
        ),
    ) {
        CharacterDetailRoute(
            onShowSnackbar = onShowSnackbar,
        )
    }
}
