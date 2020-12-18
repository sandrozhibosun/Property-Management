package com.apolis.propertymanagement.data.models

data class GetPropertyResponse(
    val data: ArrayList<Property>,
    val error: Boolean
)