package com.apolis.propertymanagement.ui.home.properties

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.apolis.propertymanagement.R
import com.apolis.propertymanagement.data.models.ImageApi
import com.apolis.propertymanagement.data.models.Property
import com.apolis.propertymanagement.databinding.ActivityPropertyBinding
import com.apolis.propertymanagement.helpers.SessionManager
import com.apolis.propertymanagement.helpers.d
import com.apolis.propertymanagement.helpers.toast
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.material.transition.platform.MaterialSharedAxis
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_property.*


class PropertyActivity : AppCompatActivity(), PropertyListener {
    lateinit var mBinding: ActivityPropertyBinding
    var propertyViewModel = PropertyViewModel()
    private val REQUEST_CAMERA_CODE = 100
    private val REQUEST_LOAD_IMAGE = 200
    var imageUri: Uri? = null
    var imageFilePath: String? = null
    var latitude:Double?=0.00
    var longitude:Double?=0.00



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        with(window) {
            window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
            var enter = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
                duration=2000L
                addTarget(R.id.property_container)

            }
            var exit = MaterialSharedAxis(MaterialSharedAxis.X, true).apply {

                addTarget(R.id.property_container)

            }
            this.enterTransition = enter
            this.exitTransition=exit
            this.allowEnterTransitionOverlap = true
//            findViewById<View>(android.R.id.content).transitionName =
//                "property_transition_container"
//            setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
//            window.sharedElementEnterTransition = MaterialContainerTransform().apply {
//                addTarget(android.R.id.content)
//                duration = 300L
//            }
//            window.sharedElementReturnTransition = MaterialContainerTransform().apply {
//                addTarget(android.R.id.content)
//                duration = 250L
//            }


        }
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_property)
        propertyViewModel = ViewModelProvider(this).get(PropertyViewModel::class.java)
        mBinding.viewmodel = propertyViewModel
        propertyViewModel.propertyListener = this
        this.d("property created")
        init()

    }


    private fun init() {
        save_tenants_button.setOnClickListener() {
            onSaved()
        }
        add_photo_property_button.setOnClickListener() {
            onAddPicture()
        }

    }


    private fun requestPermissions() {
        Dexter.withContext(
            this
        ).withPermissions(
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                if (report!!.areAllPermissionsGranted()) {

                    openGallery()
                }
                if (report.isAnyPermissionPermanentlyDenied) {
                    showDialogue()
                }
            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<PermissionRequest>?,
                token: PermissionToken?
            ) {
                token?.continuePermissionRequest()
            }

        }).onSameThread()
            .check()

    }

    private fun openGallery() {
        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.setType("image/*")
        this.d("openGallery")
        startActivityForResult(photoPickerIntent, REQUEST_LOAD_IMAGE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_LOAD_IMAGE -> {
                    this.d("load image")
                    imageUri = data!!.data
                    this.d("uri: ${imageUri.toString()}" +
                            "path:${imageUri!!.path}")
//                    Picasso.get().load(selectImage)
//                        .into(pre_view)


                }


            }

        }
    }

    private fun showDialogue() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permission")
        builder.setMessage("Please please please please give the permission")
        builder.setPositiveButton("Go to Setting", object : DialogInterface.OnClickListener {
            override fun onClick(dialogue: DialogInterface?, p1: Int) {
                dialogue?.dismiss()
                openAppSettings()
            }
        })
        builder.setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
            override fun onClick(dialogue: DialogInterface?, p1: Int) {
                dialogue?.dismiss()
            }

        }).show()
    }


    private fun openAppSettings() {
        var intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        var uri = Uri.fromParts("package", packageName, null)
        intent.setData(uri)
        startActivityForResult(intent, 101)
    }

    override fun onSaved() {

        if(imageUri==null){
            this.toast("you didn't upload your image" )
            this.d("${SessionManager(this).getUserId()!!}")
            return
        }
        else propertyViewModel.onSaveButtonClicked(imageUri!!)
        this.d("on Saved")
    }

    override fun onSuccessImage(imageResponse: LiveData<ImageApi?>) {
        imageResponse.observe(this, Observer {

            if (it == null) {
                this.toast("image upload failed")
                this.d("image upload fail")
            } else {
                this.d("image upload success")
                propertyViewModel.saveImageToUser(it,latitude!!,longitude!!,SessionManager(this).getUserId()!!)
            }

        })
    }

    override fun onAddPicture() {
        requestPermissions()
        getCurrentLocation()
        this.d("on AddPic")
    }

    override fun onStarted() {
        TODO("Not yet implemented")
    }

    override fun onSuccessProperty(propertyResponse: LiveData<Property?>) {

        propertyResponse.observe(this, Observer {
            if (it == null) {
                this.toast("property upload failed")
                this.d("property upload fail")
            } else {
                this.d("property upload success,${it.toString()}")
                finish()
            }
        })
    }

    private fun getCurrentLocation() {

        Log.d("123", "getCurrentLocation")
        var locationRequest = LocationRequest()
        locationRequest.interval=10000
        locationRequest.fastestInterval=3000
        locationRequest.priority=LocationRequest.PRIORITY_HIGH_ACCURACY
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        Log.d("123", "check permission")
        LocationServices.getFusedLocationProviderClient(this)
            .requestLocationUpdates(locationRequest, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    Log.d("123", "onLocationResult")
                    super.onLocationResult(locationResult)
                    LocationServices.getFusedLocationProviderClient(this@PropertyActivity)
                        .removeLocationUpdates(this)
                    if (locationResult != null && locationResult.getLocations().size > 0) {
                        val latestLocationIndex: Int = locationResult.getLocations().size - 1
                         latitude =
                            locationResult.getLocations().get(latestLocationIndex).latitude
                         longitude =
                            locationResult.getLocations().get(latestLocationIndex).longitude

                    }
                    Log.d("abc","Latitude: $latitude, \n Longitude: $longitude")
                    Log.d("123", "fused service")

                }
            }, Looper.myLooper())
    }

}