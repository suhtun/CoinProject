package com.su.coinproject.core.data.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.su.coinproject.features.coin.data.local.CoinDao
import com.su.coinproject.features.coin.data.local.CoinEntity

@Database(
    entities = [CoinEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val dao: CoinDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}