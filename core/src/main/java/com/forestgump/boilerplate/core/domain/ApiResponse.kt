package com.forestgump.boilerplate.core.domain

import com.google.gson.annotations.SerializedName

/**
 * Generic api response used as base type for everything that comes from the backend
 */
data class ApiResponse<T>(
    @SerializedName("data") val data: T
)
