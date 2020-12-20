package com.apolis.propertymanagement.ui.home.transactions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.apolis.propertymanagement.R
import kotlinx.android.synthetic.main.app_bar.*

class TransactionsOptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions_opt)
        init()
    }
    private fun init(){
        setupToolbar()
    }
    private fun setupToolbar() {


        var toolbar = toolbar
        toolbar.title = "Transactions"

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