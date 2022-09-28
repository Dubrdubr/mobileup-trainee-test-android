package ru.dubr.traineetestandroid.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.dubr.traineetestandroid.data.network.dto.toCoin
import ru.dubr.traineetestandroid.domain.Coin
import ru.dubr.traineetestandroid.domain.repository.CoinRepository
import ru.dubr.traineetestandroid.utils.Resource
import java.io.IOException

class GetAllCoinsUseCase(
    private val repository: CoinRepository,
) {

    suspend operator fun invoke(currency: String): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())
        try {
            val coinList = repository.getAllCoins(currency)
                .map { it.toCoin() }
            emit(Resource.Success(coinList))
        } catch (e: HttpException) {
            emit(Resource.Error("${e.code()}. ${e.message}")) // TODO: обернуть ошибку
        } catch (e: IOException) {
            emit(Resource.Error(""))
        }

    }

}