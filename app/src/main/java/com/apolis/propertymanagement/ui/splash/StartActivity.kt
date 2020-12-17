package com.apolis.propertymanagement.ui.splash

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import com.apolis.propertymanagement.ui.home.MainActivity
import com.apolis.propertymanagement.R
import com.apolis.propertymanagement.helpers.SessionManager
import com.apolis.propertymanagement.ui.auth.LoginActivity
import com.apolis.propertymanagement.ui.auth.RegisterActivity
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.google.android.material.transition.platform.MaterialSharedAxis
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity(),View.OnClickListener {
    private val delayedTime:Long =1000

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        with(window){

            //slide
//            var enter = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
//
//                addTarget(R.id.start_container)
//
//            }
            //fade
            var enter= MaterialFadeThrough().apply {
                addTarget(R.id.start_container)
                duration=2000L
            }
            window.enterTransition = enter
            window.allowEnterTransitionOverlap = true

        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        checkLogin()



    }

    override fun onResume() {
        super.onResume()
        checkLogin()
    }


    private fun init(){
        login_button.setOnClickListener(this)
        register_button.setOnClickListener(this)

    }
    private fun checkLogin()
    {
        if(SessionManager(this).isLoggedIn()){
            var intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            init()
            splash_progressbar.visibility= View.GONE
        }
    }


    override fun onClick(v: View?) {
        when(v){
            login_button->{
                startActivity(Intent(this,LoginActivity::class.java))
            }
            register_button->{startActivity(Intent(this, RegisterActivity::class.java))

            }

        }
    }
}