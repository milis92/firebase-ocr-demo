package com.forestgump.boilerplate.ui.splash

import android.os.Bundle
import com.forestgump.boilerplate.LaunchCodes
import com.forestgump.boilerplate.core.common.mvp.MvpActivity
import com.forestgump.boilerplate.ext.launchActivity
import javax.inject.Inject

class SplashActivity : MvpActivity(), SplashView {

    @Inject
    override lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        presenter.onCreate(this)

        presenter.onCreated()
    }

    override fun onLaunchHome() {
        launchActivity(className = LaunchCodes.Home)
    }

}
