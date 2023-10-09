package com.project.characterdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.project.model.data.CharacterDetail

@Composable
internal fun CharacterDetailRoute(
    modifier: Modifier = Modifier,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    viewModel: CharacterDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CharacterDetailScreen(
        modifier = modifier,
        onShowSnackbar = onShowSnackbar,
        uiState = uiState,
    )
}

@Composable
fun CharacterDetailScreen(
    modifier: Modifier,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    uiState: CharacterDetailUiState
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary,
    ) {
        Box(
            modifier = modifier
                .fillMaxSize(),
        ) {
            when (uiState) {
                CharacterDetailUiState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center),
                        color = Color.White,
                    )
                }

                is CharacterDetailUiState.Success -> {
                    CharacterDetailContent(
                        modifier = modifier,
                        characterDetail = uiState.characterDetail,
                    )
                }

                CharacterDetailUiState.Error -> {
                    Text(
                        text = "Error loading character detail",
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterDetailContent(
    modifier: Modifier,
    characterDetail: CharacterDetail
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        CharacterImage(
            imageURL = characterDetail.image,
        )

        Spacer(modifier = Modifier.height(16.dp))

        TitleAndAnswer("Character Name:", characterDetail.characterName)
        TitleAndAnswer("Location Name:", characterDetail.locationName)
        TitleAndAnswer("Location Type:", characterDetail.locationType)
        TitleAndAnswer("Location Dimension:", characterDetail.locationDimension)
        TitleAndAnswer("Number of Residents:", characterDetail.locationNumberOfResidents.toString())
    }
}

@Composable
fun TitleAndAnswer(title: String, answer: String) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = answer,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
fun CharacterImage(
    imageURL: String?,
) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }
    val imageLoader = rememberAsyncImagePainter(
        model = imageURL,
        onState = { state ->
            isLoading = state is AsyncImagePainter.State.Loading
            isError = state is AsyncImagePainter.State.Error
        },
    )
    val isLocalInspection = LocalInspectionMode.current
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        if (isLoading) {
            // Display a progress bar while loading
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.TopCenter)
                    .size(80.dp),
                color = MaterialTheme.colorScheme.secondary,
            )
        }

        Image(
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            painter = if (isError.not() && !isLocalInspection) {
                imageLoader
            } else {
                painterResource(R.drawable.ic_dummy)
            },
            contentDescription = null, // decorative image,
        )
    }
}

