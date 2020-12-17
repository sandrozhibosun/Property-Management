package com.apolis.propertymanagement.helpers

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.apolis.propertymanagement.data.models.User

class SessionManager (var mContext: Context){
    private val FILE_NAME = "login_pref"
    private val Key__Id="_Id"
    private val KEY_NAME = "name"
    private val KEY_EMAIL = "email"
    private val KEY_PASSWORD = "password"
    private val KEY_TYPE = "mobile"
    private val KEY_IS_LOGGED_IN = "isLoggedIn"



    var sharedPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    var editor = sharedPreferences.edit()

    fun isLoggedIn(): Boolean{
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    fun login(user: User): Boolean{

        editor.putString(Key__Id, user._id)
        editor.putString(KEY_NAME, user.name)
        editor.putString(KEY_EMAIL, user.email)
        editor.putString(KEY_PASSWORD, user.password)
        editor.putString(KEY_TYPE,user.type)
        editor.putBoolean(KEY_IS_LOGGED_IN, true)
        editor.commit()
        return true
    }
    fun getUserName(): String?{
        return sharedPreferences.getString(KEY_NAME, null)
    }
    fun getUserEmail(): String?{
        return sharedPreferences.getString(KEY_EMAIL, null)
    }
    fun getUserId():String?{
        return sharedPreferences.getString(Key__Id, null)
    }
    fun getUserType():String?{
        return sharedPreferences.getString(KEY_TYPE, null)
    }
    fun getUser(): User{
        var userId=sharedPreferences.getString(Key__Id,null)
        var name = sharedPreferences.getString(KEY_NAME, null)
        var email = sharedPreferences.getString(KEY_EMAIL,null)
        var password = sharedPreferences.getString(KEY_PASSWORD, null)
        var type=sharedPreferences.getString(KEY_TYPE,null)
        var user = User(_id = userId!!,name = name,email =  email, password = password,type = type)
        return user
    }
    fun logout(){
        editor.clear()
        editor.commit()
    }

}