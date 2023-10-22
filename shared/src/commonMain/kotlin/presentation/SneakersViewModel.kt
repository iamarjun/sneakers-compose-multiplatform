package presentation

import BaseViewModel
import kotlinx.coroutines.launch
import usecases.GetSneakerList

class SneakersViewModel(private val sneakers: GetSneakerList) :
    BaseViewModel<SneakersScreenContract.Event, SneakersScreenContract.State, SneakersScreenContract.Effect>() {
    override fun createInitialState(): SneakersScreenContract.State {
        return SneakersScreenContract.State()
    }

    override fun handleEvent(event: SneakersScreenContract.Event) {
        when (event) {
            is SneakersScreenContract.Event.AddToCart -> {}
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
    }


}