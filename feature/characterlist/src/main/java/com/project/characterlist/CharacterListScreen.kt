package com.project.characterlist

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.project.fetchcharacterlist.R
import com.project.model.data.CharacterListItem

@VisibleForTesting
@Composable
fun CharacterListRoute(
    onCharacterListItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharacterListViewModel = hiltViewModel(),
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
    ) {
        CharacterListScreen(
            onCharacterListItemClick = onCharacterListItemClick,
            viewModel = viewModel,
        )
    }
}

@Composable
fun CharacterListScreen(
    onCharacterListItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharacterListViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        val scrollableState = rememberLazyListState()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        when (uiState) {
            CharacterListUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center),
                    color = Color.White,
                )
            }

            is CharacterListUiState.Success -> {
                ListData(
                    onCharacterListItemClick = onCharacterListItemClick,
                    uiState = uiState,
                    scrollableState = scrollableState
                )
            }

            is CharacterListUiState.Empty -> {
                Text(
                    text = "No items found",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListData(
    onCharacterListItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    uiState: CharacterListUiState,
    scrollableState: LazyListState
) {
    val items: List<CharacterListItem> = (uiState as CharacterListUiState.Success).items

    LazyColumn(
        modifier = modifier.padding(all = 12.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 12.dp),
        state = scrollableState,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items.forEach { item ->
            item(key = item.id) {
                Surface(shape = MaterialTheme.shapes.medium) {
                    ListItem(
                        modifier = Modifier
                            .clickable {
                                onCharacterListItemClick(item.id)
                            }
                            .border(
                                shape = MaterialTheme.shapes.medium,
                                color = Color.White,
                                width = 1.dp
                            ),
                        headlineText = {
                            Text(
                                text = item.name,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color.Black,
                            )
                        },
                        supportingText = {
                            Column {
                                Text(
                                    text = item.status,
                                    color = Color.Gray,
                                )
                                Text(
                                    text = item.species,
                                    fontStyle = FontStyle.Italic,
                                    color = Color.Black,
                                )
                            }
                        },
                        leadingContent = {
                            Image(
                                painter = painterResource(id = R.drawable.ic_dummy),
                                contentDescription = null,
                                modifier = Modifier.size(48.dp)
                            )
                        },
                        colors = ListItemDefaults.colors(
                            containerColor = Color.White,
                        ),
                    )
                }
            }
        }
    }
}