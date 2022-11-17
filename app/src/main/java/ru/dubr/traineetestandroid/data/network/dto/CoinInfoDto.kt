package ru.dubr.traineetestandroid.data.network.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CoinInfoDto(
    val id: String,
    val symbol: String,
    val name: String,
    @Json(name = "asset_platform_id")
    val assetPlatformId: Any?,
    val platforms: Platforms?,
    @Json(name = "detail_platforms")
    val detailPlatforms: DetailPlatforms?,
    @Json(name = "block_time_in_minutes")
    val blockTimeInMinutes: Int?,
    @Json(name = "hashing_algorithm")
    val hashingAlgorithm: String?,
    val categories: List<String>,
    @Json(name = "public_notice")
    val publicNotice: Any?,
    @Json(name = "additional_notices")
    val additionalNotices: List<Any?>?,
    val description: Description,
    val links: Links?,
    val image: Image,
    @Json(name = "country_origin")
    val countryOrigin: String?,
    @Json(name = "genesis_date")
    val genesisDate: String?,
    @Json(name = "sentiment_votes_up_percentage")
    val sentimentVotesUpPercentage: Double?,
    @Json(name = "sentiment_votes_down_percentage")
    val sentimentVotesDownPercentage: Double?,
    @Json(name = "market_cap_rank")
    val marketCapRank: Int?,
    @Json(name = "coingecko_rank")
    val coingeckoRank: Int?,
    @Json(name = "coingecko_score")
    val coingeckoScore: Double?,
    @Json(name = "developer_score")
    val developerScore: Double?,
    @Json(name = "community_score")
    val communityScore: Double?,
    @Json(name = "liquidity_score")
    val liquidityScore: Double?,
    @Json(name = "public_interest_score")
    val publicInterestScore: Double?,
    @Json(name = "community_data")
    val communityData: CommunityData?,
    @Json(name = "public_interest_stats")
    val publicInterestStats: PublicInterestStats?,
    @Json(name = "status_updates")
    val statusUpdates: List<Any?>?,
    @Json(name = "last_updated")
    val lastUpdated: String?
)
