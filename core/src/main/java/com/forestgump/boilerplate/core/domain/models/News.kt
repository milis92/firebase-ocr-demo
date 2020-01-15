package com.forestgump.boilerplate.core.domain.models

import com.forestgump.boilerplate.core.domain.Model

data class News(val email: String) : Model {
    companion object {
        fun empty() = News("")
    }
}
