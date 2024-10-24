package com.su.coinproject.features.coin.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CoinDao {
    @Upsert
    suspend fun upsertAll(beers: List<CoinEntity>)

    @Query("SELECT * FROM coinentity")
    fun pagingSource(): PagingSource<Int, CoinEntity>

    @Query("DELETE FROM coinentity")
    suspend fun clearAll()
}