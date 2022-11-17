package ru.dubr.traineetestandroid.data.network.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Links(
    val homepage: List<String?>?,
    @Json(name = "blockchain_site")
    val blockchainSite: List<String?>?,
    @Json(name = "official_forum_url")
    val officialForumUrl: List<String?>?,
    @Json(name = "chat_url")
    val chatUrl: List<String?>?,
    @Json(name = "announcement_url")
    val announcementUrl: List<String?>?,
    @Json(name = "twitter_screen_name")
    val twitterScreenName: String?,
    @Json(name = "facebook_username")
    val facebookUsername: String?,
    @Json(name = "bitcointalk_thread_identifier")
    val bitcointalkThreadIdentifier: Any?,
    @Json(name = "telegram_channel_identifier")
    val telegramChannelIdentifier: String?,
    @Json(name = "subreddit_url")
    val subredditUrl: String?,
    @Json(name = "repos_url")
    val reposUrl: ReposUrl?
)