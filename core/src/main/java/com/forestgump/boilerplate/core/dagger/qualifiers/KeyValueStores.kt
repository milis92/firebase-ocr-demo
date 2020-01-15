package com.forestgump.boilerplate.core.dagger.qualifiers

import com.forestgump.boilerplate.core.dagger.providers.KvModule
import javax.inject.Qualifier

/**
 * [Qualifier] marking Encrypted instances of Key-Value stores
 *
 * @see KvModule
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class EncryptedKv

/**
 * [Qualifier] marking Encrypted instances of Key-Value stores
 *
 * @see KvModule
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class PlainKv
