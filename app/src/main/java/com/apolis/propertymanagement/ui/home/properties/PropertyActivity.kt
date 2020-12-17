package com.apolis.propertymanagement.ui.home.properties

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import com.apolis.propertymanagement.R
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

class PropertyActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        with(window){
            window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            findViewById<View>(android.R.id.content).transitionName= "property_transition_container"
            setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
            window.sharedElementEnterTransition = MaterialContainerTransform().apply {
                addTarget(android.R.id.content)
                duration = 300L
            }
            window.sharedElementReturnTransition = MaterialContainerTransform().apply {
                addTarget(android.R.id.content)
                duration = 250L
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)
    }
}