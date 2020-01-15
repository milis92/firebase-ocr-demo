package com.forestgump.boilerplate.ui.splash

import com.forestgump.boilerplate.core.Core
import com.forestgump.boilerplate.core.dagger.BaseActivityComponent
import com.forestgump.boilerplate.core.dagger.CoreComponent
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import com.forestgump.boilerplate.core.dagger.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
    modules = [
        SplashModule::class
    ],
    dependencies =
    [
        CoreComponent::class
    ]
)
internal abstract class SplashComponent : BaseActivityComponent<SplashActivity> {

    @Component.Factory
    interface Factory {
        fun create(@ApplicationScope coreComponent: CoreComponent): SplashComponent
    }
}

fun SplashActivity.inject() {
    DaggerSplashComponent.factory()
        .create(Core.daggerComponent)
        .inject(this)
}
