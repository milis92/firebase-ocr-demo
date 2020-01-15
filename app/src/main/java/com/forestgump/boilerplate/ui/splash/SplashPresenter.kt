package com.forestgump.boilerplate.ui.splash

import com.forestgump.boilerplate.core.common.mvp.MvpPresenter
import javax.inject.Inject

abstract class SplashPresenter : MvpPresenter<SplashView>() {
    abstract fun onStarted()
}

class SplashPresenterImp @Inject constructor() : SplashPresenter() {

    override fun onStarted() {
       view?.onLaunchHome()
    }
}
