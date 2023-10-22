package presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun DetailScreenComposable(viewModel: SneakersViewModel, onBackPress: () -> Unit) {
    val modifier = Modifier
    val sneaker by viewModel.currentSelectedSneaker.collectAsState()

    if (sneaker == null) {
        CircularProgressIndicator()
    } else
        Column(modifier = modifier.fillMaxWidth()) {
            Icon(
                modifier = modifier.padding(16.dp).size(44.dp).clickable { onBackPress() }
                    .align(Alignment.Start),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colors.primary
            )
            KamelImage(
                modifier = modifier.fillMaxWidth().fillMaxHeight(0.4f),
                resource = asyncPainterResource(sneaker?.thumbnail!!),
                contentDescription = sneaker?.brand,
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
            Spacer(modifier = modifier.height(12.dp))
            Card(
                modifier = modifier.fillMaxWidth().fillMaxHeight(),
                shape = RoundedCornerShape(15, 15),
                elevation = 12.dp
            ) {
                Column(
                    modifier = modifier.fillMaxWidth().padding(vertical = 28.dp, horizontal = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(sneaker?.shoeName!!, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                    Text(
                        "Price: $${sneaker?.retailPrice}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary
                    )
                    Button(
                        modifier = modifier,
                        onClick = {
                            viewModel.setEvent(
                                SneakersScreenContract.Event.AddToCart(
                                    sneaker!!
                                )
                            )
                        }
                    ) {
                        Text("Add to Cart")
                    }
                }
            }
        }
}

