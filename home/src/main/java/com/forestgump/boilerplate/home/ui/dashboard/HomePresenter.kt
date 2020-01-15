package com.forestgump.boilerplate.home.ui.dashboard

import com.forestgump.boilerplate.core.common.mvp.MvpPresenter
import com.forestgump.boilerplate.core.domain.UseCase
import com.forestgump.boilerplate.core.domain.interactors.GetNewsUseCase
import javax.inject.Inject

abstract class HomePresenter : MvpPresenter<HomeView>() {
    abstract fun onCreated()
}

class HomePresenterImp @Inject constructor(
        private val getNewsUseCase: GetNewsUseCase
) : HomePresenter() {

    override fun onCreated() {
        getNewsUseCase(UseCase.None, onSuccess = {}, onError = {})
    }

    override fun onDestroy() {
        super.onDestroy()
        getNewsUseCase.clear()
    }
}
