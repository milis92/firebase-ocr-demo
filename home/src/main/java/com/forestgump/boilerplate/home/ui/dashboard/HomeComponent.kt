package com.forestgump.boilerplate.home.ui.dashboard

import com.forestgump.boilerplate.core.Core
import com.forestgump.boilerplate.core.dagger.BaseActivityComponent
import com.forestgump.boilerplate.core.dagger.CoreComponent
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import com.forestgump.boilerplate.core.dagger.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
        modules = [
            HomeModule::class
        ],
        dependencies =
        [
            CoreComponent::class
        ]
)
internal abstract class HomeComponent : BaseActivityComponent<HomeActivity> {

    @Component.Factory
    interface Factory {
        fun create(@ApplicationScope coreComponent: CoreComponent): HomeComponent
    }
}

fun HomeActivity.inject() {
    DaggerHomeComponent.factory()
            .create(Core.daggerComponent)
            .inject(this)
}
