package com.forestgump.boilerplate.core.common.mvp

import androidx.annotation.CallSuper
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.annotation.VisibleForTesting.PROTECTED
import java.lang.ref.WeakReference

/**
 * Base interaction point between [MvpView] and domain layer
 */
open class MvpPresenter<T : MvpView> {

    /**
     * This reference holds the [MvpView]
     *
     * By the standard [MvpPresenter] needs to hold a reference to the [MvpView], so to prevent leaks
     * this is implemented as [WeakReference], since in our case this will usually hold a reference
     * to the something with a android lifecycle
     */
    @VisibleForTesting(otherwise = PRIVATE)
    var viewReference: WeakReference<T>? = null

    /**
     *
     * returns [MvpView] or null if Lifecycle owner is destroyed
     */
    @VisibleForTesting(otherwise = PROTECTED)
    val view
        get() = viewReference?.get()

    /**
     * Attaches the view to presenter
     *
     * @param view - instance of [MvpView]
     */
    @CallSuper
    open fun onCreate(view: T) {
        this.viewReference = WeakReference(view)
    }

    /**
     * Detaches and clears the instance of the [MvpView]
     */
    @VisibleForTesting(otherwise = PROTECTED)
    @CallSuper
    open fun onDestroy() {
        viewReference?.clear()
    }
}
