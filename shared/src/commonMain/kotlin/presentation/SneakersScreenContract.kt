package presentation

import UiEffect
import UiEvent
import UiState
import model.Sneaker

object SneakersScreenContract {

    sealed class Event : UiEvent {
        data class AddToCart(val sneaker: Sneaker) : Event()
        data class OnClick(val sneaker: Sneaker) : Event()

    }

    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }

    data class State(
        val isLoading: Boolean = false,
        val sneakers: List<Sneaker> = emptyList()
    ) : UiState
}