package ru.dubr.traineetestandroid.data.network.dto


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    val thumb: String?,
    val small: String?,
    val large: String?
)