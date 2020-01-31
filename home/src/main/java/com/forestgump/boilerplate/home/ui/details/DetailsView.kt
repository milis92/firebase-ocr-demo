package com.forestgump.boilerplate.home.ui.details

import com.forestgump.boilerplate.core.common.mvp.MvpView
import com.forestgump.boilerplate.core.domain.models.Document

interface DetailsView : MvpView {
    fun onFileLoaded(extractedText: Document)
}
