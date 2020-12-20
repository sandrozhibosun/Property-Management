package com.apolis.propertymanagement.ui.home.collectRent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.apolis.propertymanagement.R
import kotlinx.android.synthetic.main.app_bar.*

class CollectRentOptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect_rent_opt)
        init()
    }
    private fun init(){
        setupToolbar()
    }
    private fun setupToolbar() {


        var toolbar = toolbar
        toolbar.title = "Collect Rent"

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