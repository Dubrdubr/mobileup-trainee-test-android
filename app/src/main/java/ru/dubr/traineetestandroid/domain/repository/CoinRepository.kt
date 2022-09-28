package ru.dubr.traineetestandroid.domain.repository

import ru.dubr.traineetestandroid.data.network.dto.CoinDto
import ru.dubr.traineetestandroid.data.network.dto.CoinInfoDto

interface CoinRepository {

    suspend fun getAllCoins(currency: String): List<CoinDto>

    suspend fun getCoinInfo(coinId: String): CoinInfoDto
}