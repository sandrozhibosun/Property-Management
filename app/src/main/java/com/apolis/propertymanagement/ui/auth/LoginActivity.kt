package com.apolis.propertymanagement.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apolis.propertymanagement.ui.home.MainActivity
import com.apolis.propertymanagement.R
import com.apolis.propertymanagement.data.models.User
import com.apolis.propertymanagement.databinding.ActivityLoginBinding
import com.apolis.propertymanagement.helpers.SessionManager
import com.apolis.propertymanagement.helpers.d
import com.apolis.propertymanagement.helpers.toast

class LoginActivity : AppCompatActivity(),LoginListener {
    lateinit var loginViewModel: AuthViewModel
    lateinit var mBinding:ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
        loginViewModel=ViewModelProvider(this).get(AuthViewModel::class.java)
        mBinding.viewmodel=loginViewModel
        loginViewModel.loginListener=this


    }

    override fun onStarted() {
        this.toast("Started")
        this.d("started")
    }

    override fun onSuccess(loginResponse: LiveData<User?>) {

        loginResponse.observe(this, Observer {

            if(it==null)
            {
                this.d("login fail")
                this.toast("your password didn't match your email")
            }
            else {

            SessionManager(this).login(it)
                this.d("login success")
                startActivity(Intent(this, MainActivity::class.java))
            }

        })


    }

    override fun onFailure(message: String) {
        this.toast("Failure")
        this.d(message)
    }
}