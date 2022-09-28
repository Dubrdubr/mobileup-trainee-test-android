package ru.dubr.traineetestandroid.data.network.dto


import com.google.gson.annotations.SerializedName

data class ReposUrl(
    @SerializedName("github")
    val github: List<String>,
    @SerializedName("bitbucket")
    val bitbucket: List<Any>
)