package com.su.coinproject.features.coin.domain

data class Coin(
    val id: String,
    val name: String,
    val color:String,
    val symbol: String,
    val iconUrl: String,
    val price: Double,
    val marketCap: Double,
    val change: Double,
    val rank:Int,
)
