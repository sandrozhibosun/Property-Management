package com.apolis.propertymanagement.ui.home.properties

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.apolis.propertymanagement.R
import com.apolis.propertymanagement.data.models.Property
import com.apolis.propertymanagement.helpers.SessionManager
import com.google.android.material.transition.platform.MaterialFadeThrough
import com.google.android.material.transition.platform.MaterialSharedAxis
import kotlinx.android.synthetic.main.activity_property_view.*
import kotlinx.android.synthetic.main.main_content_part1.*

class PropertyViewActivity : AppCompatActivity(),PropertyViewListener {

    var propertyViewModel = PropertyViewModel()
    lateinit var propertyAdapter: PropertyAdapter

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        with(window){

            //slide
            var enter = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
                duration=2000L
                addTarget(R.id.propertyview_container)

            }
            var exit = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
                secondaryAnimatorProvider=null
                addTarget(R.id.propertyview_container)
                duration=2000L

            }
            this.enterTransition = enter
            this.exitTransition=exit
            this.allowEnterTransitionOverlap = true

        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_view)
        propertyViewModel = ViewModelProvider(this).get(PropertyViewModel::class.java)
        propertyViewModel.propertyViewListener=this
        init()

    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun init() {
        onStarted()

        propertyAdapter= PropertyAdapter(this)
        property_recycler_view.adapter=propertyAdapter

            property_recycler_view.layoutManager=
            GridLayoutManager(this, 2)


        add_property_button.setOnClickListener(){
            val intent= Intent(this, PropertyActivity::class.java)

            val options = ActivityOptions.makeSceneTransitionAnimation(
                this
//                properties_button,
//                "property_transition_container" // The transition name to be matched in Activity B.
            )
            startActivity(intent,options.toBundle())

        }
    }

    override fun onStarted() {


        propertyViewModel.getPropertyList(SessionManager(this).getUserId()!!)
    }

    override fun onGet(mList:LiveData<ArrayList<Property>>)  {

        mList.observe(this, androidx.lifecycle.Observer {
            propertyAdapter.setData(it)
    })
    }

}