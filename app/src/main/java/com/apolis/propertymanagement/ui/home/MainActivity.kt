package com.apolis.propertymanagement.ui.home

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import com.apolis.propertymanagement.R
import com.apolis.propertymanagement.helpers.SessionManager
import com.apolis.propertymanagement.ui.home.properties.PropertyActivity
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.google.android.material.transition.platform.MaterialElevationScale
import com.google.android.material.transition.platform.MaterialFadeThrough
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_content_part1.*
import kotlinx.android.synthetic.main.main_content_part3.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        with(window){
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

            var enter= MaterialFadeThrough().apply {
                addTarget(R.id.main_container)
                duration=2000L

            }

            window.enterTransition = enter
            window.allowEnterTransitionOverlap = true
            setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
            window.sharedElementsUseOverlay=false

        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init(){
        properties_button.setOnClickListener(this)
        log_out_button.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onClick(v: View?) {
        when(v){
            properties_button->{
                val intent= Intent(this,PropertyActivity::class.java)

                val options = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    properties_button,
                    "property_transition_container" // The transition name to be matched in Activity B.
                )
                startActivity(intent,options.toBundle())

            }
            log_out_button->{
                SessionManager(this).logout()
            }

        }
    }
}