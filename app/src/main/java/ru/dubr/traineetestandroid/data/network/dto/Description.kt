package ru.dubr.traineetestandroid.data.network.dto


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Description(
    val en: String
)