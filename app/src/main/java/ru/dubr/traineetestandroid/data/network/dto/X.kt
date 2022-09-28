package ru.dubr.traineetestandroid.data.network.dto


import com.google.gson.annotations.SerializedName

data class X(
    @SerializedName("decimal_place")
    val decimalPlace: Any,
    @SerializedName("contract_address")
    val contractAddress: String
)