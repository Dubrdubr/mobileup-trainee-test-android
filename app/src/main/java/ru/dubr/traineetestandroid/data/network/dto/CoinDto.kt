package ru.dubr.traineetestandroid.data.network.dto


import com.google.gson.annotations.SerializedName
import ru.dubr.traineetestandroid.domain.Coin

data class CoinDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("current_price")
    val currentPrice: Double,
    @SerializedName("market_cap")
    val marketCap: Long,
    @SerializedName("market_cap_rank")
    val marketCapRank: Double,
    @SerializedName("fully_diluted_valuation")
    val fullyDilutedValuation: Long,
    @SerializedName("total_volume")
    val totalVolume: Long,
    @SerializedName("high_24h")
    val high24h: Double,
    @SerializedName("low_24h")
    val low24h: Double,
    @SerializedName("price_change_24h")
    val priceChange24h: Double,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: Double,
    @SerializedName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double,
    @SerializedName("circulating_supply")
    val circulatingSupply: Double,
    @SerializedName("total_supply")
    val totalSupply: Double,
    @SerializedName("max_supply")
    val maxSupply: Double,
    @SerializedName("ath")
    val ath: Double,
    @SerializedName("ath_change_percentage")
    val athChangePercentage: Double,
    @SerializedName("ath_date")
    val athDate: String,
    @SerializedName("atl")
    val atl: Double,
    @SerializedName("atl_change_percentage")
    val atlChangePercentage: Double,
    @SerializedName("atl_date")
    val atlDate: String,
    @SerializedName("roi")
    val roi: Any,
    @SerializedName("last_updated")
    val lastUpdated: String
)

// Mapper
fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        symbol = symbol,
        name = name,
        image = image,
        currentPrice = currentPrice,
        priceChangePercentage24h = priceChangePercentage24h
    )
}
