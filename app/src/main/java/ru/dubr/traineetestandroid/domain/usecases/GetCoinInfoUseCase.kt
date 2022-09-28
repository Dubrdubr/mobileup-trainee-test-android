package ru.dubr.traineetestandroid.domain.usecases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.dubr.traineetestandroid.data.network.dto.toCoinInfo
import ru.dubr.traineetestandroid.domain.CoinInfo
import ru.dubr.traineetestandroid.domain.repository.CoinRepository
import ru.dubr.traineetestandroid.utils.Resource
import java.io.IOException
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    suspend operator fun invoke(coinId: String): Flow<Resource<CoinInfo>> = flow {
        emit(Resource.Loading())
        try {
            val coinInfo = repository.getCoinInfo(coinId).toCoinInfo()
            emit(Resource.Success(coinInfo))
        } catch (e: HttpException) {
            emit(Resource.Error("${e.code()}. ${e.message}")) // TODO: обернуть ошибку
        } catch (e: IOException) {
            emit(Resource.Error(""))
        }

    }
}