package epm.xnox.doggallery.presentation.home.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import epm.xnox.doggallery.common.Result
import epm.xnox.doggallery.domain.model.Dogs
import epm.xnox.doggallery.domain.useCase.GetDogsByRazaUseCase
import epm.xnox.doggallery.domain.useCase.GetRandomDogsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDogsByRazaUseCase: GetDogsByRazaUseCase,
    private val getRandomDogsUseCase: GetRandomDogsUseCase
) : ViewModel() {

    private val _state: MutableState<HomeState> = mutableStateOf(HomeState())
    val state: MutableState<HomeState> = _state

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.GetDogsByRaza -> {
                getDogsByRaza(event.raza)
            }

            is HomeEvent.GetRandomDogs -> {
                getRandomDogs(event.count)
            }
        }
    }

    private fun getDogsByRaza(raza: String) {
        viewModelScope.launch {
            getDogsByRazaUseCase(raza).collect { result ->
                when (result) {
                    is Result.Error -> {
                        _state.value = HomeState(isLoading = false)
                        _state.value = HomeState(error = result.message ?: "Error desconocido")
                    }

                    is Result.Loading -> {
                        _state.value = HomeState(isLoading = true)
                    }

                    is Result.Success -> {
                        _state.value = HomeState(isLoading = false)
                        _state.value = HomeState(data = result.data!!)
                    }
                }
            }
        }
    }

    private fun getRandomDogs(count: Int) {
        viewModelScope.launch {
            getRandomDogsUseCase(count).collect { result ->
                when (result) {
                    is Result.Error -> {
                        _state.value = HomeState(isLoading = false)
                        _state.value = HomeState(error = result.message ?: "Error desconocido")
                    }

                    is Result.Loading -> {
                        _state.value = HomeState(isLoading = true)
                    }

                    is Result.Success -> {
                        _state.value = HomeState(isLoading = false)
                        _state.value = HomeState(data = result.data!!)
                    }
                }
            }
        }
    }
}

sealed class HomeEvent {
    data class GetDogsByRaza(val raza: String) : HomeEvent()
    data class GetRandomDogs(val count: Int) : HomeEvent()
}

data class HomeState(
    val error: String = "",
    val isLoading: Boolean = false,
    val data: Dogs = Dogs(null, emptyList()),
)