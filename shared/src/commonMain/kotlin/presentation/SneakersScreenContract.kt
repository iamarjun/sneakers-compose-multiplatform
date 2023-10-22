package presentation

import UiEffect
import UiEvent
import UiState
import model.Sneaker

object SneakersScreenContract {

    sealed class Event : UiEvent {
    }

    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }

    data class State(
        val isLoading: Boolean = false,
        val sneakers: List<Sneaker> = emptyList()
    ) : UiState
}