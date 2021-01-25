package com.apolis.propertymanagement.data.repositories

import android.app.Activity
import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.core.net.toFile
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apolis.propertymanagement.data.models.*
import com.apolis.propertymanagement.data.network.MyApi
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.net.URI

class PropertyRepository() {


    fun uploadImage(realUri:String): LiveData<ImageApi?> {
        var imageApiResponse=MutableLiveData<ImageApi?>()
        var file=File(realUri)
        Log.d("abc"," file:${file.path}")



       var  reqFile:RequestBody? = RequestBody.create(MediaType.parse("multipart/form-data"),file)
        var body=MultipartBody.Part.createFormData("image",file.name,reqFile!!)
        MyApi().uploadImage(body).enqueue(object: Callback<ImageResponse> {
            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                Log.d("abc",t.message.toString())
                imageApiResponse.value=null
            }

            override fun onResponse(
                call: Call<ImageResponse>,
                response: Response<ImageResponse>
            ) {

                if(response.body()==null)
                {
                    imageApiResponse.value==null
                }
                else {imageApiResponse.value=response.body()!!.data
                Log.d("abc","success in PropertyRepository" +
                        "data: ${response.body()!!.data.location}")}
            }

        })



        return imageApiResponse

    }
    fun uploadProperty(property:Property):LiveData<Property?>{
        var propertyResponse=MutableLiveData<Property?>()

        MyApi().uploadProperty(property).enqueue(object: Callback<PropertyResponse> {
            override fun onFailure(call: Call<PropertyResponse>, t: Throwable) {
                Log.d("abc",t.message.toString())
                propertyResponse.value=null
            }

            override fun onResponse(
                call: Call<PropertyResponse>,
                response: Response<PropertyResponse>
            ) {
                if(response.body()==null)
                {
                    propertyResponse.value==null
                }
                else propertyResponse.value=response.body()!!.data
                Log.d("abc","success in PropertyRepository upload property")
            }

        })

        return propertyResponse

    }
    fun getProperties(userId:String):MutableLiveData<ArrayList<Property>>{
        var res= MutableLiveData<ArrayList<Property>>()
        MyApi().getPropertiesById(userId).enqueue(object :Callback<GetPropertyResponse>{
            override fun onFailure(call: Call<GetPropertyResponse>, t: Throwable) {
                Log.d("abc",t.message.toString())
                res.value=null
            }

            override fun onResponse(
                call: Call<GetPropertyResponse>,
                response: Response<GetPropertyResponse>
            ) {
                if(response.body()==null)
                {
                    res.value==null
                }
                else res.value=response.body()!!.data
                Log.d("abc","success in PropertyRepository get property")
            }

        })



        return res

    }


}