package com.apolis.propertymanagement.ui.home.Tenants

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.apolis.propertymanagement.R
import kotlinx.android.synthetic.main.app_bar.*

class TenantsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenants)
        init()
    }
    private fun init(){
        setupToolbar()
    }
    private fun setupToolbar() {


        var toolbar = toolbar
        toolbar.title = "Tenants"

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