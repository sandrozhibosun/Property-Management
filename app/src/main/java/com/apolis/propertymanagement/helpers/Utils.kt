package com.apolis.propertymanagement.helpers

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun Context.toast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}
fun Context.d(message: String){
    Log.d("abc",message)
}

fun ProgressBar.show(){
    this.visibility= View.VISIBLE
}
fun ProgressBar.hide(){
    this.visibility= View.GONE
}