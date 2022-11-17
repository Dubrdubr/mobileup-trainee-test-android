package ru.dubr.traineetestandroid.data.network.dto


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReposUrl(
    val github: List<String?>?,
    val bitbucket: List<Any?>?
)