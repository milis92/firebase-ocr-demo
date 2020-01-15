package com.forestgump.boilerplate.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.forestgump.boilerplate.BuildConfig

fun Activity.launchActivity(
    intent: Intent,
    requestCode: Int = -1,
    options: Bundle? = null
) {
    kotlin.runCatching {
        startActivityForResult(intent, requestCode, options)
    }
}

inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this).apply(init)
    launchActivity(intent, requestCode, options)
}

inline fun Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    className: String,
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent(className).apply(init)
    launchActivity(intent, requestCode, options)
}

fun Context.launchActivity(
    intent: Intent,
    options: Bundle? = null
) {
    kotlin.runCatching {
        startActivity(intent, options)
    }
}

inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this).apply(init)
    launchActivity(intent, options)
}

inline fun Context.launchActivity(
    options: Bundle? = null,
    className: String,
    init: Intent.() -> Unit = {}
) {
    val intent = newIntent(className).apply(init)
    launchActivity(intent, options)
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
        Intent(context, T::class.java)

fun newIntent(clazz: String): Intent =
        Intent(Intent.ACTION_VIEW).setClassName(BuildConfig.APPLICATION_ID, clazz)
