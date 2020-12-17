package com.apolis.propertymanagement.ui.auth

import androidx.lifecycle.LiveData
import com.apolis.propertymanagement.data.models.User

interface RegisterListener {
    fun onStarted()
    fun onSuccess(registerResponse: LiveData<User?>)
    fun onFailure(message: String)
}