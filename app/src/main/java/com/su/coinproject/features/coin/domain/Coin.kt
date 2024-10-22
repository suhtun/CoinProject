package com.su.coinproject.features.coin.domain

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val price: Double,
    val change: Double
)
