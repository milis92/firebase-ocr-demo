package com.forestgump.boilerplate.home.ui.dashboard

import com.forestgump.boilerplate.core.dagger.scopes.FeatureScope
import dagger.Binds
import dagger.Module

@Module
internal abstract class HomeModule {

    @FeatureScope
    @Binds
    abstract fun bindHomePresenter(homePresenterImp: HomePresenterImp): HomePresenter
}
