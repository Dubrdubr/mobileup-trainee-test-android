package ru.dubr.traineetestandroid.data.repository

import ru.dubr.traineetestandroid.data.network.CoinApi
import ru.dubr.traineetestandroid.domain.repository.CoinRepository

class CoinRepositoryImpl(
    private val coinApi: CoinApi
) : CoinRepository {

    override suspend fun getAllCoins(currency: String) {
        coinApi.getAllCoins(currency)
    }

    override suspend fun getCoinInfo(coinId: String) {
        coinApi.getCoinInfo(coinId)
    }
}