package com.su.coinproject.di

import com.su.coinproject.core.data.remote.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClientFactory.create(CIO.create())
    }
}