package com.forestgump.boilerplate.core.dagger.providers

import android.content.Context
import androidx.room.Room
import com.forestgump.boilerplate.core.BuildConfig
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import com.forestgump.boilerplate.core.domain.AppDatabase
import dagger.Module
import dagger.Provides

/**
 * This module provides various instances of local storage implementations
 */
@Module
internal class LocalStorageModule {

    /**
     * Provides instance of the local database
     */
    @ApplicationScope
    @Provides
    fun provideLocalDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.LIBRARY_PACKAGE_NAME)
            .enableMultiInstanceInvalidation()
            .build()
}
