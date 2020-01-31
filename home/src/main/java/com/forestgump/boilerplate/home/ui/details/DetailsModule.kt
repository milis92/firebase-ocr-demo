package com.forestgump.boilerplate.home.ui.details

import com.forestgump.boilerplate.core.dagger.scopes.FeatureScope
import dagger.Binds
import dagger.Module

@Module
internal abstract class DetailsModule {

    @FeatureScope
    @Binds
    abstract fun bindPresenter(presenter: DetailsPresenterImpl): DetailsPresenter
}
