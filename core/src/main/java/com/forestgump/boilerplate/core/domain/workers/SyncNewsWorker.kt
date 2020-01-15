package com.forestgump.boilerplate.core.domain.workers

import android.content.Context
import androidx.work.RxWorker
import androidx.work.WorkerParameters
import com.forestgump.boilerplate.core.dagger.qualifiers.IoScheduler
import com.forestgump.boilerplate.core.domain.WorkerProvider
import com.forestgump.boilerplate.core.domain.repositories.NewsRepository
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import io.reactivex.Scheduler
import io.reactivex.Single

class SyncNewsWorker
@AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: NewsRepository,
    @IoScheduler private val scheduler: Scheduler
) : RxWorker(context, params) {

    override fun createWork(): Single<Result> {
        return repository.sync()
                .toSingle { Result.success() }
                .onErrorReturn { Result.retry() }
    }

    override fun getBackgroundScheduler(): Scheduler {
        return scheduler
    }

    @AssistedInject.Factory
    interface Factory : WorkerProvider.Factory
}
