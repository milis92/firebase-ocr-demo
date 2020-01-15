package com.forestgump.boilerplate.core.dagger.providers

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.forestgump.boilerplate.core.BuildConfig
import com.forestgump.boilerplate.core.dagger.qualifiers.EncryptedKv
import com.forestgump.boilerplate.core.dagger.qualifiers.PlainKv
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

/**
 * This module provides Key-Value stores for the app
 *
 * ANDROIDDEP Generify KeyValue stores
 */
@Module
internal class KvModule {

    /**
     * Provides encrypted Key-Value store based on [EncryptedSharedPreferences]
     * TODO See if we can change this implementation with something different so we could increase min api to 21
     */
    @ApplicationScope
    @Provides
    @EncryptedKv
    fun provideEncryptedKv(context: Context): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            "${BuildConfig.LIBRARY_PACKAGE_NAME}.encrypted",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    /**
     * Provides plain Key-Value store to be used for values that do not require secure context
     */
    @Provides
    @PlainKv
    fun provideKv(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "${BuildConfig.LIBRARY_PACKAGE_NAME}.plain",
            Context.MODE_PRIVATE
        )
    }
}
