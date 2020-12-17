package com.apolis.propertymanagement.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.apolis.propertymanagement.data.models.LoginResponse
import com.apolis.propertymanagement.data.models.RegisterResponse
import com.apolis.propertymanagement.data.models.User
import com.apolis.propertymanagement.data.network.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {
fun loginUser(email:String,password:String): LiveData<User?> {
    val loginResponse= MutableLiveData<User?>()
    var user= User(email=email,password = password)
    MyApi().login(user)
        .enqueue(object: Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("abc",t.message.toString())
                loginResponse.value=null
            }

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {

                if(response.body()==null)
                {
                    loginResponse.value==null
                }
                else loginResponse.value=response.body()!!.user
                Log.d("abc","success in AuthRepository")
            }

        })
    return loginResponse

}
fun registerUser(user: User): LiveData<User?> {
    val registerResponse= MutableLiveData<User?>()
    MyApi().register(user)
        .enqueue(object :retrofit2.Callback<RegisterResponse>{
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.d("abc",t.message.toString())
                registerResponse.value=null
            }

            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if(response.body()==null)
                {
                    registerResponse.value=null
                }
                else registerResponse.value=response.body()!!.data
                Log.d("abc","success in AuthRepository")
            }

        })


    return registerResponse

}}