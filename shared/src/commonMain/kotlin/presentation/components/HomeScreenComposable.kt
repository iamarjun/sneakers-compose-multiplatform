package presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import presentation.SneakersScreenContract
import presentation.SneakersViewModel

@Composable
fun HomeScreenComposable(viewModel: SneakersViewModel, openDetailScreen: () -> Unit) {
    val modifier = Modifier
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
                Card(
                    modifier = modifier.fillMaxWidth().clickable {
                        viewModel.setEvent(SneakersScreenContract.Event.OnClick(it))
                        openDetailScreen()
                    },
                    shape = RoundedCornerShape(10),
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = modifier.fillMaxWidth().padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            KamelImage(
                                modifier = modifier.fillMaxWidth(),
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
                            Icon(
                                modifier = modifier.align(Alignment.TopEnd).size(26.dp)
                                    .clickable {
                                        viewModel.setEvent(
                                            SneakersScreenContract.Event.AddToCart(
                                                it
                                            )
                                        )
                                    },
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = MaterialTheme.colors.primary
                            )
                        }
                        Text(it.shoeName, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                        Text(
                            "$ ${it.retailPrice}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primary
                        )
                    }
                }
            }
        }
    }
}
