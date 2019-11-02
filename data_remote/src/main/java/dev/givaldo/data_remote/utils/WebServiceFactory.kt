package dev.givaldo.data_remote.utils

import com.google.gson.internal.GsonBuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dev.givaldo.data_remote.constants.ProjectConstants.CONNECT_TIMEOUT
import dev.givaldo.data_remote.constants.ProjectConstants.READ_TIMEOUT
import dev.givaldo.data_remote.constants.ProjectConstants.WRITE_TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.reflect.Type

object WebServiceFactory {

    inline fun <reified T> createWebService(url: String): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(provideOkHttpClient())
            .addConverterFactory(UnitConverterFactory)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create()
    }

    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .connectTimeout(CONNECT_TIMEOUT.first, CONNECT_TIMEOUT.second)
            .readTimeout(READ_TIMEOUT.first, READ_TIMEOUT.second)
            .writeTimeout(WRITE_TIMEOUT.first, WRITE_TIMEOUT.second)
            .build()

    private fun httpLoggingInterceptor() = HttpLoggingInterceptor().apply {
//        if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
    }

    object UnitConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type, annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *>? {
            return if (type == Unit::class.java) UnitConverter else null
        }

        private object UnitConverter : Converter<ResponseBody, Unit> {
            override fun convert(value: ResponseBody) {
                value.close()
            }
        }
    }
}