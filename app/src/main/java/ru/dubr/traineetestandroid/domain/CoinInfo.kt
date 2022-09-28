package ru.dubr.traineetestandroid.domain

import ru.dubr.traineetestandroid.data.network.dto.Image

data class CoinInfo(
    val id: String,
    val symbol: String,
    val name: String,
    val categories: List<String>,
    val description: String,
    val image: Image,
)