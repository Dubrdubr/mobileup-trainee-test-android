package ru.dubr.traineetestandroid.data.repository

import ru.dubr.traineetestandroid.data.network.CoinApi
import ru.dubr.traineetestandroid.data.network.dto.CoinDto
import ru.dubr.traineetestandroid.data.network.dto.CoinInfoDto
import ru.dubr.traineetestandroid.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val coinApi: CoinApi
) : CoinRepository {

    override suspend fun getAllCoins(currency: String): List<CoinDto> {
        return coinApi.getAllCoins(currency)
    }

    override suspend fun getCoinInfo(coinId: String): CoinInfoDto {
        return coinApi.getCoinInfo(coinId)
    }
}