package com.forestgump.boilerplate.ui.splash

import android.os.Bundle
import com.forestgump.boilerplate.core.common.mvp.MvpActivity
import javax.inject.Inject

class SplashActivity : MvpActivity(), SplashView {

    @Inject
    override lateinit var presenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        presenter.onCreate(this)
    }

    override fun onLaunchHome() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
