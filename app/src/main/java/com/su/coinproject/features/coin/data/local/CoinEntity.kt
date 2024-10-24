package com.su.coinproject.features.coin.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinEntity(
    @PrimaryKey
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