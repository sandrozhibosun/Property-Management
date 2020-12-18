package com.apolis.propertymanagement.data.models

data class Property(
    val __v: Int?=0,
    val _id: String?=null,
    val address: String?=null,
    val city: String?=null,
    val country: String="US",
    val image: String?=null,
    val latitude: String?=null,
    val longitude: String?=null,
    val mortageInfo: Boolean?=null,
    val propertyStatus: Boolean?=null,
    val purchasePrice: String?=null,
    val state: String?=null,
    val userId: String?=null
)