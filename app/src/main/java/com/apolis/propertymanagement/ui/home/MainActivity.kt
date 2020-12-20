package com.apolis.propertymanagement.ui.home

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import com.apolis.propertymanagement.R
import com.apolis.propertymanagement.helpers.SessionManager
import com.apolis.propertymanagement.ui.home.Tenants.TenantsActivity
import com.apolis.propertymanagement.ui.home.collectRent.CollectRentOptActivity
import com.apolis.propertymanagement.ui.home.document.DocumentsActivity
import com.apolis.propertymanagement.ui.home.properties.PropertyActivity
import com.apolis.propertymanagement.ui.home.properties.PropertyViewActivity
import com.apolis.propertymanagement.ui.home.todoList.ToDoListActivity
import com.apolis.propertymanagement.ui.home.transactions.TransactionsOptActivity
import com.apolis.propertymanagement.ui.home.trips.TripsActivity
import com.google.android.material.transition.platform.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.main_content_part1.*
import kotlinx.android.synthetic.main.main_content_part3.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        with(window){

            var enter= MaterialFadeThrough().apply {
                addTarget(R.id.main_container)
                duration=2000L

            }
            var exit = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
                secondaryAnimatorProvider=null
                duration=2000L
            addTarget(R.id.main_container)

            }

            window.enterTransition = enter
            window.exitTransition=exit
            window.allowEnterTransitionOverlap = true
            //scale
//            setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
//            window.sharedElementsUseOverlay=false

        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

    }

    private fun init(){

        setupToolbar()

        properties_button.setOnClickListener(this)
        log_out_button.setOnClickListener(this)
        Tenants_button.setOnClickListener(this)
        collect_rent_button.setOnClickListener(this)
        to_do_list_button.setOnClickListener(this)
        trips_button.setOnClickListener(this)
        documents_button.setOnClickListener(this)
        vendors_button.setOnClickListener (this)
        transaction_button.setOnClickListener(this)
    }

    private fun setupToolbar() {


        var toolbar = toolbar
        toolbar.title = "Main Console"

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {

                finish()
            }
        }
        return true
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onClick(v: View?) {
        when(v){
            properties_button->{
                val intent= Intent(this, PropertyViewActivity::class.java)

                val options = ActivityOptions.makeSceneTransitionAnimation(
                    this
//                    properties_button,
//                    "property_transition_container" // The transition name to be matched in Activity B.
                )
                startActivity(intent,options.toBundle())

            }
            log_out_button->{
                SessionManager(this).logout()
            }
            Tenants_button->{
                val intent= Intent(this, TenantsActivity::class.java)
                startActivity(intent)

            }
            transaction_button->{
                val intent= Intent(this, TransactionsOptActivity::class.java)
                startActivity(intent)
            }
            collect_rent_button->{
                val intent= Intent(this, CollectRentOptActivity::class.java)
                startActivity(intent)

            }
            to_do_list_button->{
                val intent= Intent(this, ToDoListActivity::class.java)
                startActivity(intent)

            }
            trips_button->{
                val intent= Intent(this, TripsActivity::class.java)
                startActivity(intent)
            }
            documents_button->{
                val intent= Intent(this, DocumentsActivity::class.java)
                startActivity(intent)

            }
            vendors_button->{

            }

        }
    }
}