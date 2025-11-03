package com.vivek.data.repository

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("title")
    val title: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("resolution")
    val resolution: String?
)