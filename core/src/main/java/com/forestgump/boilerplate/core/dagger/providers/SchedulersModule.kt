package com.forestgump.boilerplate.core.dagger.providers

import com.forestgump.boilerplate.core.dagger.qualifiers.*
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * This module provides different RxSchedulers
 */
@Module
internal class SchedulersModule {

    /**
     * Provides io scheduler
     *
     * Use this scheduler for IO operations like network requests, file access etc
     * This scheduler is backed by thread pool with variable number of cores
     */
    @ApplicationScope
    @Provides
    @IoScheduler
    fun ioScheduler() = Schedulers.io()

    /**
     * Provides main thread scheduler
     */
    @ApplicationScope
    @Provides
    @MainScheduler
    fun mainScheduler() = AndroidSchedulers.mainThread()

    /**
     * Provide computation scheduler
     *
     * This scheduler is backed by thread pool but opposed to IO scheduler, this one runs on
     * fixed number of threads (number of threads == number of cores on the system)
     */
    @ApplicationScope
    @Provides
    @Computation
    fun computationScheduler() = Schedulers.computation()

    /**
     * Provides new thread scheduler
     *
     * This scheduler will spawn a new tread every on new execution, use with caution
     */
    @ApplicationScope
    @Provides
    @NewThreadScheduler
    fun newThreadScheduler() = Schedulers.newThread()

    /**
     * Provide Single thread scheduler
     *
     * Every job will be executed on a single thread, no matter how many subscriber it has
     */
    @ApplicationScope
    @Provides
    @SingleScheduler
    fun singleScheduler() = Schedulers.single()
}
