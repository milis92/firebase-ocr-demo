package com.forestgump.boilerplate.home.ui.details

import com.forestgump.boilerplate.core.Core
import com.forestgump.boilerplate.core.dagger.BaseActivityComponent
import com.forestgump.boilerplate.core.dagger.CoreComponent
import com.forestgump.boilerplate.core.dagger.scopes.ApplicationScope
import com.forestgump.boilerplate.core.dagger.scopes.FeatureScope
import dagger.Component

@FeatureScope
@Component(
        modules = [
            DetailsModule::class
        ],
        dependencies =
        [
            CoreComponent::class
        ]
)
internal abstract class DetailsComponent : BaseActivityComponent<DetailsActivity> {

    @Component.Factory
    interface Factory {
        fun create(@ApplicationScope coreComponent: CoreComponent): DetailsComponent
    }
}

fun DetailsActivity.inject() {
    DaggerDetailsComponent.factory()
            .create(Core.daggerComponent)
            .inject(this)
}
