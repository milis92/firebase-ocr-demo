package com.forestgump.boilerplate.home.ui.dashboard

import android.content.SharedPreferences
import android.os.Bundle
import com.forestgump.boilerplate.core.common.mvp.MvpActivity
import com.forestgump.boilerplate.core.dagger.qualifiers.PlainKv
import com.forestgump.boilerplate.home.R
import javax.inject.Inject

class HomeActivity : MvpActivity(), HomeView{


    @Inject
    override lateinit var presenter: HomePresenter

    @Inject
    @PlainKv
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        inject()

        presenter.onCreate(this)

        presenter.onCreated()
    }
}
