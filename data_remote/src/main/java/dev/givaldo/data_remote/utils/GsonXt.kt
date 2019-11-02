package dev.givaldo.data_remote.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> String.parseJson(): T = Gson().fromJson<T>(this, object : TypeToken<T>() {}.type)
