package com.forestgump.boilerplate.core.domain.services

import com.forestgump.boilerplate.core.domain.ApiResponse
import com.forestgump.boilerplate.core.domain.entities.NewsEntity
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface NewsRemoteService {

    @Headers("X-Anonymous: true")
    @POST("/news")
    fun getAll(@Body body: HashMap<String, String>): Single<ApiResponse<NewsEntity>>
}
