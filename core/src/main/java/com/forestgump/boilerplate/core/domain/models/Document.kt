package com.forestgump.boilerplate.core.domain.models

import android.net.Uri
import com.forestgump.boilerplate.core.domain.Model

data class Document(val id: String, val extractedText: String,val uri: Uri) : Model {

    companion object {
        fun empty() = Document("", "", Uri.EMPTY)
    }
}
