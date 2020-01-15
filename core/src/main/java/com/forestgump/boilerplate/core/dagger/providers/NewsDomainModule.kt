package com.forestgump.boilerplate.core.dagger.providers

import android.content.SharedPreferences
import com.forestgump.boilerplate.core.dagger.qualifiers.Api
import com.forestgump.boilerplate.core.dagger.qualifiers.EncryptedKv
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import com.forestgump.boilerplate.core.domain.AppDatabase
import com.forestgump.boilerplate.core.domain.daos.NewsLocalService
import com.forestgump.boilerplate.core.domain.repositories.NewsRepository
import com.forestgump.boilerplate.core.domain.repositories.NewsRepositoryImpl
import com.forestgump.boilerplate.core.domain.services.NewsRemoteService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class NewsDomainModule {

    @ApplicationScope
    @Provides
    internal fun provideRemoteService(@Api retrofit: Retrofit): NewsRemoteService =
            retrofit.create(NewsRemoteService::class.java)

    @ApplicationScope
    @Provides
    internal fun provideLocalService(database: AppDatabase): NewsLocalService =
            database.news()


    @ApplicationScope
    @Provides
    internal fun provideRepository(
            remoteService: NewsRemoteService,
            localService: NewsLocalService,
            @EncryptedKv encryptedKv: SharedPreferences
    ): NewsRepository =
            NewsRepositoryImpl(remoteService, localService, encryptedKv)
}
