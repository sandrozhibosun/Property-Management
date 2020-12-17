package com.apolis.propertymanagement.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apolis.propertymanagement.R
import com.apolis.propertymanagement.data.models.User
import com.apolis.propertymanagement.helpers.d
import com.apolis.propertymanagement.helpers.toast
import com.apolis.propertymanagement.ui.auth.fragments.RegisterFragment
import com.apolis.propertymanagement.ui.auth.fragments.RegisterVPAdapter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(),RegisterFragment.OnFragmentInteraction,RegisterListener {

    var registerVpAdapter=
        RegisterVPAdapter(
            supportFragmentManager
        )
    lateinit var registerViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        init()
    }

    private fun init(){
        register_view_pager.adapter=registerVpAdapter
        register_tab_layout.setupWithViewPager(register_view_pager)
        registerViewModel=ViewModelProvider(this).get(AuthViewModel::class.java)
        registerViewModel.registerListener=this

    }

    override fun onRegisButtonClicked(user: User) {
        registerViewModel.onRegister(user)
    }

    override fun onStarted() {
        this.d("Start")
    }

    override fun onSuccess(registerResponse: LiveData<User?>) {
        registerResponse.observe(this, Observer {

            if(it==null)
            {
                this.toast("your email has been registered")
                this.d("regis fail")
            }
            else {
                this.d("regis success")
                startActivity(Intent(this, RegisterActivity::class.java))
            }

        })
    }

    override fun onFailure(message: String) {

        this.toast(message)
        this.d(message)
    }


}