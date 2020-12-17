package com.apolis.propertymanagement.data.models

data class LoginResponse(
    val token: String,
    var user: User
)