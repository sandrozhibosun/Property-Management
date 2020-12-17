package com.apolis.propertymanagement.data.network

import com.apolis.propertymanagement.app.Config
import com.apolis.propertymanagement.data.models.LoginResponse
import com.apolis.propertymanagement.data.models.RegisterResponse
import com.apolis.propertymanagement.data.models.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApi {
    @POST("auth/login")
    fun login(@Body user: User): Call<LoginResponse>

    @POST("auth/register")
    fun register(@Body user: User): Call<RegisterResponse>

    companion object{
        operator fun invoke():MyApi{
            return Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}