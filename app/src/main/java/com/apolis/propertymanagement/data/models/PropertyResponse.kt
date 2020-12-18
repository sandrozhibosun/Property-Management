package com.apolis.propertymanagement.data.models

data class PropertyResponse(
    val data: Property,
    val error: Boolean,
    val message: String
)