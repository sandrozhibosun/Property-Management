package com.apolis.propertymanagement.ui.home.properties

import androidx.lifecycle.LiveData
import com.apolis.propertymanagement.data.models.Property

interface PropertyViewListener{
    fun onStarted()
    fun onGet(mList: LiveData<ArrayList<Property>>)
}