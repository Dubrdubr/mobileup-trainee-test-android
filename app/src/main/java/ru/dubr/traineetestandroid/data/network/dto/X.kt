package ru.dubr.traineetestandroid.data.network.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class X(
    @Json(name = "decimal_place")
    val decimalPlace: Any?,
    @Json(name = "contract_address")
    val contractAddress: String?
)