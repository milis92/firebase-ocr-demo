package com.forestgump.boilerplate.core.dagger.qualifiers

import com.forestgump.boilerplate.core.dagger.providers.SchedulersModule
import javax.inject.Qualifier

/**
 * [Qualifier] marking the instances of IoScheduler
 *
 * @see SchedulersModule
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoScheduler

/**
 * [Qualifier] marking the instances of IoScheduler
 *
 * @see SchedulersModule
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainScheduler

/**
 * [Qualifier] marking the instances of IoScheduler
 *
 * @see SchedulersModule
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Computation

/**
 * [Qualifier] marking the instances of IoScheduler
 *
 * @see SchedulersModule
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class NewThreadScheduler

/**
 * [Qualifier] marking the instances of IoScheduler
 *
 * @see SchedulersModule
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class SingleScheduler
