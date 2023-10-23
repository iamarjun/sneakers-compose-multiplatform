package presentation

import BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import model.Sneaker
import usecases.CartManager
import usecases.GetSneakerList

class SneakersViewModel(
    private val sneakers: GetSneakerList,
    private val cartManager: CartManager
) : BaseViewModel<SneakersScreenContract.Event, SneakersScreenContract.State, SneakersScreenContract.Effect>() {

    val currentSelectedSneaker by lazy { MutableStateFlow<Sneaker?>(null) }

    val total
        get() = cartManager.totalCartPrice

    val sneaker
        get() = cartManager.sneaker

    override fun createInitialState(): SneakersScreenContract.State {
        return SneakersScreenContract.State()
    }

    override fun handleEvent(event: SneakersScreenContract.Event) {
        when (event) {
            is SneakersScreenContract.Event.AddToCart -> {
                viewModelScope.launch {
                    cartManager.saveSneakerToCart(event.sneaker)
                }
            }

            is SneakersScreenContract.Event.OnClick -> {
                viewModelScope.launch {
                    currentSelectedSneaker.emit(event.sneaker)
                }
            }

            is SneakersScreenContract.Event.RemoveFromCart -> {
                viewModelScope.launch {
                    cartManager.deleteSneakerFromCart(event.sneaker)
                }
            }

            else -> {}
        }
    }

    init {
        viewModelScope.launch {
            setState {
                copy(
                    isLoading = true
                )
            }
            sneakers()
                .onSuccess {
                    setState {
                        copy(
                            isLoading = false,
                            sneakers = it
                        )
                    }
                }
                .onFailure {
                    setState {
                        copy(
                            isLoading = false,
                        )
                    }

                    setEffect {
                        SneakersScreenContract.Effect.ShowToast(
                            it.message ?: "Something went wrong"
                        )
                    }
                }
        }

        viewModelScope.launch {
            cartManager.totalCartPrice.collectLatest {
                println("----------- $it")
            }
        }
    }


}