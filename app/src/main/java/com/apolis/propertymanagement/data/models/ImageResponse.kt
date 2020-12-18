package com.apolis.propertymanagement.data.models

data class ImageResponse(
    val data: ImageApi,
    val error: Boolean,
    val message: String
)