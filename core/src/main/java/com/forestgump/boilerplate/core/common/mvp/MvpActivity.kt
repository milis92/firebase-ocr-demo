package com.forestgump.boilerplate.core.common.mvp

import android.view.MenuItem
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity

/**
 * Base abstraction to be used with any activity that wants to take advantage of MVP features.
 */
abstract class MvpActivity : AppCompatActivity() {

    abstract val presenter: MvpPresenter<*>

    /**
     * Destroys the presenter and cancels all ongoing jobs
     */
    @CallSuper
    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    /**
     * Default handler for home action items
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
