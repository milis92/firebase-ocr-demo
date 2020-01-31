package com.forestgump.boilerplate.home.ui.dashboard

import com.forestgump.boilerplate.core.common.mvp.MvpView
import com.forestgump.boilerplate.core.domain.models.Document

interface HomeView : MvpView {
    fun onDocumentsLoaded(documents: List<Document>)
    fun onDocumentProcessingError(throwable: Throwable?)

}
