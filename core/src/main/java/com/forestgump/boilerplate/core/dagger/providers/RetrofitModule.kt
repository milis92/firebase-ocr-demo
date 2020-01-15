package com.forestgump.boilerplate.core.dagger.providers

import com.forestgump.boilerplate.core.BuildConfig
import com.forestgump.boilerplate.core.dagger.qualifiers.Api
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This module provides Retrofit instances
 *
 * @see [Retrofit][https://square.github.io/retrofit/]
 */
@Module
internal class RetrofitModule {

    /**
     * Provides Retrofit Proxy for the BE domains
     */
    @Api
    @ApplicationScope
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .validateEagerly(true)
            .build()
}
