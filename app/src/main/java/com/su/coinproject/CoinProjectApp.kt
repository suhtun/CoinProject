package com.su.coinproject

import android.app.Application
import com.su.coinproject.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CoinProjectApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CoinProjectApp)
            androidLogger()

            modules(appModule)
        }
    }
}