package com.forestgump.boilerplate.core.dagger.qualifiers

import com.forestgump.boilerplate.core.dagger.providers.RetrofitModule
import javax.inject.Qualifier

/**
 * [Qualifier] marking the BE instances of Retrofit Client
 *
 * @see RetrofitModule
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class Api
