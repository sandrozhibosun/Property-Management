package com.apolis.propertymanagement.data.network

import com.apolis.propertymanagement.app.Config
import com.apolis.propertymanagement.data.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface MyApi {
    @POST("auth/login")
    fun login(@Body user: User): Call<LoginResponse>

    @POST("auth/register")
    fun register(@Body user: User): Call<RegisterResponse>

    @Multipart
    @POST("upload/property/picture")
    fun uploadImage(@Part image: MultipartBody.Part? ):Call<ImageResponse>

    @POST("property")
    fun uploadProperty(@Body property:Property):Call<PropertyResponse>

    @GET("property/user/{userId}")
    fun getPropertiesById(@Path("userId") userId:String):Call<GetPropertyResponse>


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