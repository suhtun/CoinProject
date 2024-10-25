package com.su.coinproject.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> fromJson(json: String): T {
    return Gson().fromJson(json, object : TypeToken<T>() {}.type)
}