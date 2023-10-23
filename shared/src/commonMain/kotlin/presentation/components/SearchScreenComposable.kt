package presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import presentation.SneakersScreenContract
import presentation.SneakersViewModel

@Composable
fun CartScreenComposable(viewModel: SneakersViewModel) {
    val modifier = Modifier

    val sneakers = viewModel.sneaker

    val subtotal by viewModel.total.collectAsState()

    val list = sneakers.values.toList()

    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier.fillMaxWidth().fillMaxHeight(0.6f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(list) { sneaker ->
                Box(
                    modifier = modifier.fillMaxWidth().padding(8.dp)
                        .height(125.dp)
                        .clickable {
//                    viewModel.setEvent(SneakersScreenContract.Event.OnClick(it))
//                    openDetailScreen()
                        }
                ) {
                    Card(
                        modifier = modifier.fillMaxWidth().padding(horizontal = 8.dp),
                        shape = RoundedCornerShape(10),
                        elevation = 6.dp
                    ) {
                        Row(
                            modifier = modifier.fillMaxWidth().padding(8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            KamelImage(modifier = modifier.weight(0.45f),
                                resource = asyncPainterResource(sneaker?.thumbnail!!),
                                contentDescription = sneaker?.brand,
                                onLoading = {
                                    CircularProgressIndicator(
                                        modifier = modifier.size(16.dp).align(Alignment.Center),
                                        strokeWidth = 1.dp
                                    )
                                },
                                onFailure = {
                                    println("----- $it")
                                })
                            Column(modifier = modifier.weight(0.55f)) {
                                Text(
                                    sneaker?.shoeName!!,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    "$ ${sneaker?.retailPrice}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colors.primary
                                )
                            }
                        }
                    }
                    Icon(
                        modifier = modifier.align(Alignment.TopEnd)
                            .size(22.dp)
                            .background(
                                color = MaterialTheme.colors.primary,
                                shape = RoundedCornerShape(50)
                            )
                            .clickable {
                                viewModel.setEvent(
                                    SneakersScreenContract.Event.RemoveFromCart(
                                        sneaker!!
                                    )
                                )
                            },
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }

        Column(modifier = modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                "Order Detail",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier.height(8.dp))
            Text(
                "Subtotal: $subtotal",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                "Taxes and charges: ${subtotal * 0.10}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                "Total: ${subtotal + (subtotal * 0.10)}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            )
            Button(onClick = {}) {
                Text("Checkout")
            }
        }
    }
}
