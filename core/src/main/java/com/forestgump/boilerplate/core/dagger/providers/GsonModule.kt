package com.forestgump.boilerplate.core.dagger.providers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This module provides [Gson] instance
 *
 * @see [Gson][https://github.com/google/gson]
 */
@Module
internal class GsonModule {

    /**
     * Provides [Gson] to be used in oder receivers such as [GsonConverterFactory]
     * This instance will serialise nulls by default,
     */
    @ApplicationScope
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .serializeNulls()
        .create()

    /**
     * Creates [GsonConverterFactory]
     */
    @ApplicationScope
    @Provides
    fun provideConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)
}
