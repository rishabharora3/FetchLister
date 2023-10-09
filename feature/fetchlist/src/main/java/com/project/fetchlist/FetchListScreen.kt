package com.project.fetchlist

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
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
import com.project.model.data.ListItem

import com.project.ui.ListFeedUiState

@VisibleForTesting
@Composable
fun ListItems(
    modifier: Modifier = Modifier,
    viewModel: FetchListerViewModel = hiltViewModel(),
) {
    Surface(
        modifier = modifier.windowInsetsPadding(
            WindowInsets.navigationBars.only(WindowInsetsSides.Start + WindowInsetsSides.End)
        ),
        color = MaterialTheme.colorScheme.primary,
    ) {
        FetchListScreen(
            viewModel = viewModel,
        )
    }
}

@Composable
fun FetchListScreen(
    modifier: Modifier = Modifier,
    viewModel: FetchListerViewModel
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val scrollableState = rememberLazyListState()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

        when (uiState) {
            ListFeedUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center),
                    color = Color.White,
                )
            }

            is ListFeedUiState.Success -> {
                ListData(
                    uiState = uiState,
                    scrollableState = scrollableState
                )
            }

            is ListFeedUiState.Empty -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "No items found",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ListData(
    modifier: Modifier = Modifier,
    uiState: ListFeedUiState,
    scrollableState: LazyListState
) {
    val items: List<ListItem> = (uiState as ListFeedUiState.Success).items

    LazyColumn(
        modifier = modifier.padding(all = 12.dp),
        contentPadding = PaddingValues(top = 12.dp, bottom = 12.dp),
        state = scrollableState,
    ) {
        items.forEach { item ->
            item(key = item.id) {
                ListItem(
                    modifier = Modifier.clickable { },
                    headlineText = {
                        Text(
                            text = "ID: ${item.id}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black,
                        )
                    },
                    overlineText = {
                        Text(
                            text = "List ID: ${item.listId}",
                            color = Color.Gray,
                        )
                    },
                    supportingText = {
                        Text(
                            text = "Name: ${item.name}",
                            fontStyle = FontStyle.Italic,
                            color = Color.Black,
                        )
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
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}