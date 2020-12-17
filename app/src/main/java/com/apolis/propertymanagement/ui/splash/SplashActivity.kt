package com.apolis.propertymanagement.ui.splash

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.transition.Explode

import android.view.Window
import androidx.annotation.RequiresApi
import com.apolis.propertymanagement.ui.home.MainActivity

import com.apolis.propertymanagement.R
import com.apolis.propertymanagement.helpers.SessionManager
import com.google.android.material.transition.platform.MaterialElevationScale
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.google.android.material.transition.platform.MaterialSharedAxis

class SplashActivity : AppCompatActivity() {
    private val delayedTime:Long = 2000



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        with(window){

//            // slide
//            val exit = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {
//
//               addTarget(R.id.splash_container)
//            }
            //fade
            val exit= MaterialFadeThrough().apply {

                // Only run the transition on the contents of this activity, excluding
                // system bars or app bars if provided by the app’s theme.
                addTarget(R.id.splash_container)
                duration=2000L
            }

            window.exitTransition = exit

        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        var handler = Handler()
        handler.postDelayed({
            checkLogin()

        },delayedTime)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun checkLogin()
    {
        if(SessionManager(this).isLoggedIn()){

            startActivity(Intent(this,
                MainActivity::class.java),
                ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
            finish()
        }else{
            startActivity(Intent(this,StartActivity::class.java),
            ActivityOptions.makeSceneTransitionAnimation(this).toBundle())

        }
    }

}