package com.forestgump.boilerplate.core.dagger.providers

import com.forestgump.boilerplate.core.dagger.mapkeys.WorkerClass
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import com.forestgump.boilerplate.core.domain.WorkerProvider
import com.forestgump.boilerplate.core.domain.workers.SyncDocumentsWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WorkerFactoryModule {

    @ApplicationScope
    @Binds
    @IntoMap
    @WorkerClass(SyncDocumentsWorker::class)
    internal abstract fun bindDataSyncWorker(factory: SyncDocumentsWorker.Factory):
        WorkerProvider.Factory

}
