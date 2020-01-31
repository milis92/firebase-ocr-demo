package com.forestgump.boilerplate.core.dagger.providers

import android.content.Context
import com.forestgump.boilerplate.core.dagger.qualifiers.Api
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import com.forestgump.boilerplate.core.domain.AppDatabase
import com.forestgump.boilerplate.core.domain.daos.DocumentsLocalService
import com.forestgump.boilerplate.core.domain.repositories.DocumentRepository
import com.forestgump.boilerplate.core.domain.repositories.DocumentRepositoryImpl
import com.forestgump.boilerplate.core.domain.services.DocumentsRemoteService
import com.google.firebase.ml.vision.document.FirebaseVisionDocumentTextRecognizer
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
internal class NewsDomainModule {

    @ApplicationScope
    @Provides
    internal fun provideRemoteService(@Api retrofit: Retrofit): DocumentsRemoteService =
            retrofit.create(DocumentsRemoteService::class.java)

    @ApplicationScope
    @Provides
    internal fun provideLocalService(database: AppDatabase): DocumentsLocalService =
            database.news()


    @ApplicationScope
    @Provides
    internal fun provideRepository(
            context: Context,
            localService: DocumentsLocalService,
            textDetector: FirebaseVisionTextRecognizer
    ): DocumentRepository =
            DocumentRepositoryImpl(context, localService, textDetector)
}
