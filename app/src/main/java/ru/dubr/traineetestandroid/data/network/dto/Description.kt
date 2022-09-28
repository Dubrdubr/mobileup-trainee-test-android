package ru.dubr.traineetestandroid.data.network.dto


import com.google.gson.annotations.SerializedName

data class Description(
    @SerializedName("en")
    val en: String
)