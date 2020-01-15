package com.forestgump.boilerplate.core

import android.content.ContentProvider
import android.content.ContentValues
import android.net.Uri
import com.forestgump.boilerplate.core.dagger.CoreComponent
import com.forestgump.boilerplate.core.dagger.DaggerCoreComponent
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

/**
 * No-op content provider used to start and init this library on the app start
 */
class Core : ContentProvider() {

    companion object {
        lateinit var daggerComponent: CoreComponent
    }

    override fun onCreate(): Boolean {
        // Fail fast if context cant be provided
        daggerComponent = DaggerCoreComponent.factory()
            .create(context!!)

        RxJavaPlugins.setErrorHandler {
            Timber.e(it)
        }

        return true
    }

    override fun insert(uri: Uri, values: ContentValues?) = null

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ) = null

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ) = 0

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) = 0

    override fun getType(uri: Uri) = null
}
