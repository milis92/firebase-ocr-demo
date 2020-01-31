package com.forestgump.boilerplate.home.ui.dashboard

import android.net.Uri
import com.forestgump.boilerplate.core.common.mvp.MvpPresenter
import com.forestgump.boilerplate.core.domain.UseCase
import com.forestgump.boilerplate.core.domain.interactors.GetProcessedDocumentsUseCase
import com.forestgump.boilerplate.core.domain.interactors.ProcessImageUseCase
import javax.inject.Inject

abstract class HomePresenter : MvpPresenter<HomeView>() {
    abstract fun onCreated()
    abstract fun onImageLoaded(uri: Uri)
}

class HomePresenterImp @Inject constructor(
        private val processImageUseCase: ProcessImageUseCase,
        private val getProcessedDocumentsUseCase: GetProcessedDocumentsUseCase
) : HomePresenter() {

    override fun onCreated() {
        getProcessedDocumentsUseCase(UseCase.None, onNext = {
            view?.onDocumentsLoaded(it)
        })
    }

    override fun onImageLoaded(uri: Uri) {
        processImageUseCase(ProcessImageUseCase.Params(uri), onError = {
            view?.onDocumentProcessingError(it.throwable)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        processImageUseCase.clear()
    }
}
