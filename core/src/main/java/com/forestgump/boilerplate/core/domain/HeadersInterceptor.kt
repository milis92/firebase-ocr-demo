package com.forestgump.boilerplate.core.domain

import android.content.SharedPreferences
import android.text.TextUtils
import com.forestgump.boilerplate.core.dagger.qualifiers.EncryptedKv
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * [Interceptor] implementation which attaches default and required headers to each request
 *
 * If request is passing X-Anonymous header, this implementation will *not* attach X-Authorize
 * token to the request
 */
class HeadersInterceptor
@Inject constructor(
    @EncryptedKv private val sharedPreferences: SharedPreferences,
    private val defaultHeaders: Headers
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request().newBuilder()
            .headers(defaultHeaders)
            .build()
        try {
            if (!TextUtils.isEmpty(chain.request().header("X-Anonymous"))) {
                return chain.proceed(request)
            } else {
                val accessToken = sharedPreferences.getString("token", null)
                if (accessToken == null) {
                    return chain.proceed(request)
                } else {
                    request = chain.request().newBuilder()
                        .headers(defaultHeaders)
                        .header("Authorization", "Bearer $accessToken")
                        .build()
                }
            }
        } catch (exception: Throwable) {
            Timber.e(exception)
        }

        return chain.proceed(request)
    }
}
