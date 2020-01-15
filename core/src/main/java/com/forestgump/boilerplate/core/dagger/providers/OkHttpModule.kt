package com.forestgump.boilerplate.core.dagger.providers

import com.forestgump.boilerplate.core.BuildConfig
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import com.forestgump.boilerplate.core.domain.HeadersInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.Headers
import okhttp3.Headers.Companion.headersOf
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * This module provides OkHttpClient instances
 *
 * @see [OkHttpClient][https://square.github.io/okhttp/]
 */
@Module
internal class OkHttpModule {

    /**
     * Provides logging [okhttp3.Interceptor] interceptor that prints request to console in debug builds
     */
    @ApplicationScope
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    /**
     * Provides default SSL certificate fingerprints for any certificates issued by AWS Certificate manager
     */
    private fun awsRootCertificateFingerprints() = listOf(
        "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=",
        "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=",
        "sha256/KwccWaCgrnaw6tsrrSO61FgLacNgG2MMLq8GE6+oP5I="
    )

    /**
     * Provides OkHttpClient certificate pinner for Smallpdf BE domains
     * TODO Update Certificate pinner pattern to your host and your cert fingerprints
     */
    @ApplicationScope
    @Provides
    fun provideCertificatePinner(): CertificatePinner = CertificatePinner.Builder()
        .add("yourdomain.com", *awsRootCertificateFingerprints().toTypedArray())
        .add("staging.yourdomain.com", *awsRootCertificateFingerprints().toTypedArray())
        .build()

    /**
     * Provides [OkHttpClient.Builder] to be used whenever we need new instance of OkHttpClient
     */
    @ApplicationScope
    @Provides
    fun provideOkHttpClientBuilder(certificatePinner: CertificatePinner) =
        OkHttpClient.Builder().apply {
            followRedirects(true)
            followSslRedirects(true)
            certificatePinner(certificatePinner)
        }

    /**
     * Provides list of default [Headers] required by the app
     */
    @ApplicationScope
    @Provides
    fun provideDefaultHeaders(): Headers = headersOf(
        "Content-Type", "application/json",
        "Accept", "application/json",
        "X-Client-Platform", "android",
        "X-Client-Version", BuildConfig.VERSION_NAME
    )

    /**
     * Provides application specific OkHttpClient
     * This instance should be used only by the application itself and not by 3rd party services
     * (like Glide for example).
     * If you need instance of the OkHttpClient build a new one using the output of [provideOkHttpClientBuilder]
     */
    @ApplicationScope
    @Provides
    fun provideOkHttpClient(
        okHttpClientBuilder: OkHttpClient.Builder,
        loggingInterceptor: HttpLoggingInterceptor,
        headersInterceptor: HeadersInterceptor
    ): OkHttpClient =
        okHttpClientBuilder.apply {
            addInterceptor(headersInterceptor)
            addInterceptor(loggingInterceptor)
        }.build()
}
