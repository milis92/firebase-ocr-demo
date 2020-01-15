package com.forestgump.boilerplate.core.domain

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

/**
 * Worker factory used by Dagger and Assisted inject to provide runtime bindings for the di graph
 */
class AppWorkerFactory @Inject constructor(
    private val workerFactories: Map<Class<out ListenableWorker>,
        @JvmSuppressWildcards Provider<WorkerProvider.Factory>>
) : WorkerFactory() {

    override fun createWorker(
        context: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        val foundEntry = workerFactories.entries.find {
            Class.forName(workerClassName).isAssignableFrom(it.key)
        }
        val factoryProvider = foundEntry?.value
            ?: throw IllegalArgumentException("Unknown worker class: $workerClassName")
        return factoryProvider.get().create(context, workerParameters)
    }
}

class WorkerProvider {
    interface Factory {
        fun create(context: Context, params: WorkerParameters): ListenableWorker
    }
}
