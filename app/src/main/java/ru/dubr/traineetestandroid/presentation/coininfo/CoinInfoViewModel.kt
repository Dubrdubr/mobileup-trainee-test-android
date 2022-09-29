package ru.dubr.traineetestandroid.presentation.coininfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.dubr.traineetestandroid.domain.CoinInfo
import ru.dubr.traineetestandroid.domain.usecases.GetCoinInfoUseCase
import ru.dubr.traineetestandroid.utils.DispatcherProvider
import ru.dubr.traineetestandroid.utils.Resource
import javax.inject.Inject

@HiltViewModel
class CoinInfoViewModel @Inject constructor(
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val dispatcher: DispatcherProvider,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val navArgs = CoinInfoFragmentArgs.fromSavedStateHandle(savedStateHandle)
    private val coinId = navArgs.coinId
    private val coinName = navArgs.coinName

    private val _state = MutableLiveData<ViewStateCoinInfo>()
    val state: LiveData<ViewStateCoinInfo> = _state

    init {
        getCoinInfo()
    }

    fun getCoinInfo() {
        getCoinInfoUseCase(coinId).onEach { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _state.postValue(ViewStateCoinInfo(isLoading = true))
                }
                is Resource.Error -> {
                    _state.postValue(ViewStateCoinInfo(
                        error = resource.error ?: "Unexpected error",
                        showError = true)
                    )
                }
                is Resource.Success -> {
                    _state.postValue(ViewStateCoinInfo(coinInfo = resource.data, showInfoContainer = true))
                }
            }
        }.launchIn(CoroutineScope(dispatcher.io))
    }

    data class ViewStateCoinInfo(
        val coinInfo: CoinInfo? = null,
        val isLoading: Boolean = false,
        val showInfoContainer: Boolean = false,
        val showError: Boolean = false,
        val error: String = "",
    )
}

