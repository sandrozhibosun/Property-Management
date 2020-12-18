package com.apolis.propertymanagement.ui.home.properties

import androidx.lifecycle.LiveData
import com.apolis.propertymanagement.data.models.ImageApi
import com.apolis.propertymanagement.data.models.ImageResponse
import com.apolis.propertymanagement.data.models.Property

interface PropertyListener {
    fun onSaved()
    fun onSuccessImage(imageResponse: LiveData<ImageApi?>)
    fun onAddPicture()
    fun onStarted()
    fun onSuccessProperty(propertyResponse:LiveData<Property?>)

}