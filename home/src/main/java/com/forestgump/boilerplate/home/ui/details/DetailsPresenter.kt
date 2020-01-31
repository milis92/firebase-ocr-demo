package com.forestgump.boilerplate.home.ui.details

import com.forestgump.boilerplate.core.common.mvp.MvpPresenter
import com.forestgump.boilerplate.core.domain.interactors.GetProcessedDocumentUseCase
import javax.inject.Inject

abstract class DetailsPresenter : MvpPresenter<DetailsView>() {
    abstract fun onCreated(file: String)
}

class DetailsPresenterImpl @Inject constructor(
        private val getProcessedDocument: GetProcessedDocumentUseCase
) : DetailsPresenter() {

    override fun onCreated(file: String) {
        getProcessedDocument(GetProcessedDocumentUseCase.Params(file), onSuccess = {
            view?.onFileLoaded(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
