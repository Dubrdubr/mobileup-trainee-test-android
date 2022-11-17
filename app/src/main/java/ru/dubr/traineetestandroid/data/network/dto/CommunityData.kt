package ru.dubr.traineetestandroid.data.network.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CommunityData(
    @Json(name = "facebook_likes")
    val facebookLikes: Any?,
    @Json(name = "twitter_followers")
    val twitterFollowers: Int?,
    @Json(name = "reddit_average_posts_48h")
    val redditAveragePosts48h: Int?,
    @Json(name = "reddit_average_comments_48h")
    val redditAverageComments48h: Double?,
    @Json(name = "reddit_subscribers")
    val redditSubscribers: Int?,
    @Json(name = "reddit_accounts_active_48h")
    val redditAccountsActive48h: Int?,
    @Json(name = "telegram_channel_user_count")
    val telegramChannelUserCount: Any?
)