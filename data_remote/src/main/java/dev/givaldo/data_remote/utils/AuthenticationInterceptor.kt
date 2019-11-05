package dev.givaldo.data_remote.utils

import dev.givaldo.data_remote.constants.ApiConstants.API_KEY
import dev.givaldo.data_remote.constants.ApiConstants.API_KEY_PATH
import dev.givaldo.data_remote.constants.ApiConstants.API_LANGUAGE
import dev.givaldo.data_remote.constants.ApiConstants.API_LANGUAGE_PATH
import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(API_LANGUAGE_PATH, API_LANGUAGE)
            .addQueryParameter(API_KEY_PATH, API_KEY)
            .build()

        val request = original.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}