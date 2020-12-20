package com.apolis.propertymanagement.ui.home.trips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.apolis.propertymanagement.R
import kotlinx.android.synthetic.main.app_bar.*

class TripsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)
        init()
    }
    private fun init(){
        setupToolbar()
    }
    private fun setupToolbar() {


        var toolbar = toolbar
        toolbar.title = "Add Trip"

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
}