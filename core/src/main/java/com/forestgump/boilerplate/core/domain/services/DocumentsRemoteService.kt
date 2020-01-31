package com.forestgump.boilerplate.core.domain.services

import com.forestgump.boilerplate.core.domain.ApiResponse
import com.forestgump.boilerplate.core.domain.entities.DocumentEntity
import io.reactivex.Single
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface DocumentsRemoteService {

    @Headers("X-Anonymous: true")
    @POST("/documents")
    fun getAll(): Single<ApiResponse<DocumentEntity>>
}
