package ru.dubr.traineetestandroid.domain.repository

interface CoinRepository {

    suspend fun getAllCoins(currency: String)

    suspend fun getCoinInfo(coinId: String)
}