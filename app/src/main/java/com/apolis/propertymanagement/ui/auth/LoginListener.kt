package com.apolis.propertymanagement.ui.auth

import android.os.Message
import androidx.lifecycle.LiveData
import com.apolis.propertymanagement.data.models.User

interface LoginListener{
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<User?>)
    fun onFailure(message: String)
}