package com.forestgump.boilerplate.ui.splash

import com.forestgump.boilerplate.core.dagger.scopes.FeatureScope
import dagger.Binds
import dagger.Module

@Module
abstract class SplashModule {

    @FeatureScope
    @Binds
    abstract fun bindSplashPresenter(splashPresenterImp: SplashPresenterImp): SplashPresenter
}
