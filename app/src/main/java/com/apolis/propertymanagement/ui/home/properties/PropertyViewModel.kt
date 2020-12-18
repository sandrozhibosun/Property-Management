package com.apolis.propertymanagement.ui.home.properties

import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apolis.propertymanagement.data.models.ImageApi
import com.apolis.propertymanagement.data.models.ImageResponse
import com.apolis.propertymanagement.data.models.Property
import com.apolis.propertymanagement.data.repositories.PropertyRepository
import com.apolis.propertymanagement.helpers.SessionManager

class PropertyViewModel :ViewModel(){
    var propertyListener:PropertyListener?=null
    var propertyViewListener:PropertyViewListener?=null
    var propertuRepository= PropertyRepository()

    var address:String?=null
    var city:String?=null
    var state:String?=null
    var zip:String?=null
    var country="US"
    var mortgageCheck=false
    var multiUnitCheck=false
    var purchasePrice:String?=null
    var dashBoardCheck=false


    fun onSaveButtonClicked(imageUri:Uri)
    {
    var imageResponse=propertuRepository.uploadImage(imageUri)
    propertyListener?.onSuccessImage(imageResponse)

    }
    fun saveImageToUser(imageApi: ImageApi,latitude:Double,longitude:Double,userId:String){
        var property=Property(propertyStatus = multiUnitCheck,mortageInfo = mortgageCheck,image =imageApi.location,
        address = address,city = city,country = "US",purchasePrice = purchasePrice,state = state,
        latitude = latitude.toString(),longitude = longitude.toString(),userId =userId )
        var propertyResponse=propertuRepository.uploadProperty(property)
        propertyListener?.onSuccessProperty(propertyResponse)

    }

    fun getPropertyList(userId: String){
        var mList=propertuRepository.getProperties(userId)
        propertyViewListener?.onGet(mList)


    }

}