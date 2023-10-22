import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chrynan.navigation.ExperimentalNavigationApi
import com.chrynan.navigation.compose.NavigationContainer
import com.chrynan.navigation.compose.rememberNavigator
import com.chrynan.navigation.push
import navigation.AppDestination
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class, ExperimentalNavigationApi::class)
@Composable
fun App() {
    MaterialTheme {
        val navigator = rememberNavigator(initialDestination = AppDestination.HOME)
        Scaffold(
            bottomBar = {
                val currentDestination by navigator.store.destination.changes.collectAsState(initial = AppDestination.HOME)
                BottomNavigation {
                    AppDestination.values().forEach { destination ->
                        BottomNavigationItem(
                            selected = currentDestination == destination,
                            onClick = { navigator.push(destination) },
                            icon = {
                                Icon(
                                    imageVector = when (destination) {
                                        AppDestination.HOME -> Icons.Default.Home
                                        AppDestination.CART -> Icons.Default.ShoppingCart
                                    },
                                    contentDescription = destination.name
                                )
                            },
                            label = { Text(destination.name) },
                        )
                    }
                }
            }
        ) {
            NavigationContainer(navigator = navigator) { (destination, context) ->
                when (destination) {
                    AppDestination.HOME -> HomeScreenComposable()
                    AppDestination.CART -> SearchScreenComposable()
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SearchScreenComposable() {
    var greetingText by remember { mutableStateOf("Hello, World!") }
    var showImage by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            greetingText = "Hello, ${getPlatformName()}"
            showImage = !showImage
        }) {
            Text(greetingText)
        }
        AnimatedVisibility(showImage) {
            Image(
                painterResource("compose-multiplatform.xml"),
                null
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomeScreenComposable() {
    var greetingText by remember { mutableStateOf("Hello, World!") }
    var showImage by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            greetingText = "Hello, ${getPlatformName()}"
            showImage = !showImage
        }) {
            Text(greetingText)
        }
        AnimatedVisibility(showImage) {
            Image(
                painterResource("compose-multiplatform.xml"),
                null
            )
        }
    }
}

expect fun getPlatformName(): String