package com.apolis.propertymanagement.data.models

data class RegisterResponse(
    val data: User,
    val error: Boolean,
    val message: String
)