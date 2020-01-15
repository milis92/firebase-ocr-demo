package com.forestgump.boilerplate.core.dagger.providers

import dagger.Module

/**
 * Utility module that binds rest of the providers together
 */
@Module(
    includes = [
        LocalStorageModule::class,
        GsonModule::class,
        RetrofitModule::class,
        OkHttpModule::class,
        KvModule::class,
        SchedulersModule::class,
        WorkerModule::class
    ]
)
internal class CoreModule
