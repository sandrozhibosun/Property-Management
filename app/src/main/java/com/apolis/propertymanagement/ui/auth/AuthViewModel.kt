package com.apolis.propertymanagement.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.apolis.propertymanagement.data.models.User
import com.apolis.propertymanagement.data.repositories.AuthRepository

class AuthViewModel :ViewModel(){
    var loginListener: LoginListener?=null
    var registerListener:RegisterListener?=null
    var authRepository=AuthRepository()
    var email:String?=null
    var password:String?=null


    fun onRegister(user: User){
        registerListener?.onStarted()
        var registerResponse=authRepository.registerUser(user)
        registerListener?.onSuccess(registerResponse)

    }

    fun onLogin(view:View){
        loginListener?.onStarted()
        if(email.isNullOrEmpty()||password.isNullOrEmpty()){
            //fail
            loginListener?.onFailure("can't be null")
            return
        }
        //to do
        var loginResponse= AuthRepository().loginUser(email!!,password!!)

            loginListener?.onSuccess(loginResponse)}



}