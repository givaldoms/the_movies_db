package dev.givaldo.data_remote.utils

import dev.givaldo.data_remote.constants.ApiConstants.BASE_URL
import dev.givaldo.data_remote.constants.ProjectConstants.CONNECT_TIMEOUT
import dev.givaldo.data_remote.constants.ProjectConstants.READ_TIMEOUT
import dev.givaldo.data_remote.constants.ProjectConstants.WRITE_TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object WebServiceFactory {

    inline fun <reified T> createWebService(): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create()
    }

    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .addInterceptor(AuthenticationInterceptor())
            .connectTimeout(CONNECT_TIMEOUT.first, CONNECT_TIMEOUT.second)
            .readTimeout(READ_TIMEOUT.first, READ_TIMEOUT.second)
            .writeTimeout(WRITE_TIMEOUT.first, WRITE_TIMEOUT.second)
            .build()

    private fun httpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

}