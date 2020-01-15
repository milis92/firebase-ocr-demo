package com.forestgump.boilerplate.core.dagger.providers

import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.WorkManager
import com.forestgump.boilerplate.core.BuildConfig
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import com.forestgump.boilerplate.core.domain.AppWorkerFactory
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        WorkerAssistedModule::class,
        WorkerFactoryModule::class
    ]
)
class WorkerModule {

    @ApplicationScope
    @Provides
    fun provideWorkManager(
        context: Context,
        workerFactory: AppWorkerFactory
    ): WorkManager {
        WorkManager.initialize(
            context,
            Configuration.Builder()
                .setWorkerFactory(workerFactory)
                .setMinimumLoggingLevel(if (BuildConfig.DEBUG) Log.VERBOSE else Log.INFO)
                .build()
        )

        return WorkManager.getInstance(context)
    }
}
