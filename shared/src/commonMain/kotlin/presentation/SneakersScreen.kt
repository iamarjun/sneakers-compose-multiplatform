package presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun SneakersScreen(viewModel: SneakersViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) {

        val modifier = Modifier.padding(it)
        val state by viewModel.uiState.collectAsState()

        if (state.isLoading) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                println("--------- ${state.sneakers}")
                items(state.sneakers) {
                    KamelImage(
                        modifier = modifier.fillMaxWidth().aspectRatio(1.0f),
                        resource = asyncPainterResource(it.thumbnail),
                        contentDescription = it.brand,
                        contentScale = ContentScale.FillWidth,
                        onLoading = {
                            CircularProgressIndicator(
                                modifier = modifier.size(16.dp).align(Alignment.Center),
                                strokeWidth = 1.dp
                            )
                        },
                        onFailure = {
                            println("----- $it")
                        }
                    )
                }
            }
        }
    }
}