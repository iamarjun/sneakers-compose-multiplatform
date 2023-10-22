import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
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
import com.chrynan.navigation.ExperimentalNavigationApi
import com.chrynan.navigation.compose.NavigationContainer
import com.chrynan.navigation.compose.rememberNavigator
import com.chrynan.navigation.popDestination
import com.chrynan.navigation.push
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import navigation.AppDestination
import org.koin.compose.getKoin
import presentation.SneakersViewModel
import presentation.components.CartScreenComposable
import presentation.components.DetailScreenComposable
import presentation.components.HomeScreenComposable
import usecases.CartManager
import usecases.GetSneakerList

@OptIn(ExperimentalNavigationApi::class)
@Composable
fun App() {
    MaterialTheme {
        val getSneakers: GetSneakerList = getKoin().get()
        val cartManager: CartManager = getKoin().get()
        val viewModel =
            getViewModel(Unit, viewModelFactory { SneakersViewModel(getSneakers, cartManager) })
        val navigator = rememberNavigator(initialDestination = AppDestination.HOME)
        Scaffold(
            bottomBar = {
                val currentDestination by navigator.store.destination.changes.collectAsState(initial = AppDestination.HOME)
                BottomNavigation {
                    (AppDestination.values()
                        .toList() - AppDestination.DETAIL).forEach { destination ->
                        BottomNavigationItem(
                            selected = currentDestination == destination,
                            onClick = { navigator.push(destination) },
                            icon = {
                                Icon(
                                    imageVector = when (destination) {
                                        AppDestination.HOME -> Icons.Default.Home
                                        AppDestination.CART -> Icons.Default.ShoppingCart
                                        else -> throw IllegalArgumentException("Destination $destination not meant to be in the bottom navigation")
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
                    AppDestination.HOME -> HomeScreenComposable(viewModel) {
                        navigator.push(AppDestination.DETAIL)
                    }

                    AppDestination.CART -> CartScreenComposable(viewModel)
                    AppDestination.DETAIL -> DetailScreenComposable(viewModel) {
                        navigator.popDestination()
                    }
                }
            }
        }
    }
}

expect fun getPlatformName(): String