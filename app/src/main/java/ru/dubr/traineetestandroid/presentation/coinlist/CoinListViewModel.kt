package ru.dubr.traineetestandroid.presentation.coinlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.dubr.traineetestandroid.data.network.dto.toCoin
import ru.dubr.traineetestandroid.domain.Coin
import ru.dubr.traineetestandroid.domain.repository.CoinRepository
import ru.dubr.traineetestandroid.domain.usecases.GetAllCoinsUseCase
import ru.dubr.traineetestandroid.utils.Const
import ru.dubr.traineetestandroid.utils.DispatcherProvider
import ru.dubr.traineetestandroid.utils.Resource
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getAllCoinsUseCase: GetAllCoinsUseCase,
    private val dispatcher: DispatcherProvider,
) : ViewModel() {

    private val _state = MutableLiveData<ViewState>()
    val state: LiveData<ViewState> = _state

    init {
        viewModelScope.launch {
            getAllCoins()
        }
    }

    fun getAllCoins(currency: String = Const.DEFAULT_CURRENCY) {
        getAllCoinsUseCase(currency).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.postValue(ViewState(isLoading = true))
                }
                is Resource.Error -> {
                    _state.postValue(ViewState(
                        error = resource.error ?: "Unexpected error",
                        showError = true)
                    )
                }
                is Resource.Success -> {
                    _state.postValue(ViewState(coinList = resource.data ?: emptyList()))
                }
            }
        }.launchIn(CoroutineScope(dispatcher.io))
    }

    data class ViewState(
        val coinList: List<Coin> = emptyList(),
        val isLoading: Boolean = false,
        val showError: Boolean = false,
        val error: String = "",
    )
}