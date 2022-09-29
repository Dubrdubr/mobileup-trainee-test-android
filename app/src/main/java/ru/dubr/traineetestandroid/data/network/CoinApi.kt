package ru.dubr.traineetestandroid.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.dubr.traineetestandroid.Api
import ru.dubr.traineetestandroid.data.network.dto.CoinDto
import ru.dubr.traineetestandroid.data.network.dto.CoinInfoDto

interface CoinApi {

    @GET("/api/v3/coins/markets")
    suspend fun getAllCoins(
        @Query(QUERY_PARAM_VS_CURRENCY) vsCurrency: String = "usd",
        @Query(QUERY_PARAM_ORDER) order: String = "market_cap_desc",
        @Query(QUERY_PARAM_PER_PAGE) perPage: Int = NUMBER_PER_PAGE,
        @Query(QUERY_PARAM_PAGE) page: Int = NUMBER_OF_PAGES,
        @Query(QUERY_PARAM_SPARKLINE) sparkline: Boolean = false,
    ): List<CoinDto>

    @GET("/api/v3//coins/{coinId}")
    suspend fun getCoinInfo(
        @Path(PATH_PARAM_COIN_ID) coinId: String,
        @Query(QUERY_PARAM_LOCALIZATION) localization: Boolean = false,
        @Query(QUERY_PARAM_TICKERS) tickers: Boolean = false,
        @Query(QUERY_PARAM_MARKET_DATA) marketData: Boolean = false,
        @Query(QUERY_PARAM_DEVELOPER_DATA) devData: Boolean = false,
    ): CoinInfoDto

    companion object {
        private const val QUERY_PARAM_VS_CURRENCY = "vs_currency"
        private const val QUERY_PARAM_ORDER = "order"
        private const val QUERY_PARAM_PER_PAGE = "per_page"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_SPARKLINE = "sparkline"

        private const val PATH_PARAM_COIN_ID = "coinId"
        private const val QUERY_PARAM_LOCALIZATION = "localization"
        private const val QUERY_PARAM_TICKERS = "tickers"
        private const val QUERY_PARAM_MARKET_DATA = "market_data"
        private const val QUERY_PARAM_DEVELOPER_DATA = "developer_data"

        private const val NUMBER_PER_PAGE = 30
        private const val NUMBER_OF_PAGES = 1
    }
}