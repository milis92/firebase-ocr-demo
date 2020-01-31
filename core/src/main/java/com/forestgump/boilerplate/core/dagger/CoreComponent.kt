package com.forestgump.boilerplate.core.dagger

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import com.forestgump.boilerplate.core.dagger.providers.CoreModule
import com.forestgump.boilerplate.core.dagger.providers.NewsDomainModule
import com.forestgump.boilerplate.core.dagger.qualifiers.PlainKv
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import com.forestgump.boilerplate.core.domain.interactors.GetProcessedDocumentUseCase
import com.forestgump.boilerplate.core.domain.interactors.GetProcessedDocumentsUseCase
import com.forestgump.boilerplate.core.domain.interactors.ProcessImageUseCase
import dagger.BindsInstance
import dagger.Component

/**
 * Dagger core component for the app.
 *
 * This component provides and configures core dependencies for the app. If you want to use dependencies
 * from this graph use this component as dagger component dependency
 *
 * Every dependency that needs to be exposed to other components needs to be specified as
 * abstract method with return type equal to the requested dependency
 */
@ApplicationScope
@Component(
    modules = [
        CoreModule::class,
        NewsDomainModule::class
    ]
)
abstract class CoreComponent : BaseComponent<Application> {

    @PlainKv
    abstract fun sharedPreferences(): SharedPreferences

    abstract fun processImageUseCase(): ProcessImageUseCase

    abstract fun getProcessedDocumentsUseCase() : GetProcessedDocumentsUseCase

    abstract fun getProcessedDocumentUseCase() : GetProcessedDocumentUseCase

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }
}

interface BaseComponent<T> {
    fun inject(target: T)
}

interface BaseActivityComponent<T : Activity> : BaseComponent<T>

interface BaseFragmentComponent<T : Fragment> : BaseComponent<T>

interface BaseServiceComponent<T : Service> : BaseComponent<T>
