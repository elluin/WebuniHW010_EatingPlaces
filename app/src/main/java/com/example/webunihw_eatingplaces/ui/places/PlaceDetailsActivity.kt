package com.example.webunihw_eatingplaces.ui.places

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.webunihw_eatingplaces.R
import com.example.webunihw_eatingplaces.databinding.ActivityPlacedetailsBinding
import com.example.webunihw_eatingplaces.model.places.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException


class PlaceDetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var where = LatLng(47.458649, 18.9486852)
    private lateinit var binding: ActivityPlacedetailsBinding
    private val placedetailsViewModel: PlaceDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlacedetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.setTitle("Hely profil")
        }

        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.mapPlacedetailFrg) as SupportMapFragment
        mapFragment.getMapAsync(this)

        placedetailsViewModel.getPlaceDetailsLiveData()
            .observe(this, { uploadResult -> render(uploadResult) })


    }//ONCREATE

    override fun onResume() {
        super.onResume()
        val place = intent.extras!!.getString("KEY_PLACE")
        if (place != null) {
            placedetailsViewModel.getPlaceDetails(place)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let {
            mMap = googleMap
            mMap.uiSettings.isCompassEnabled = true
            mMap.uiSettings.isZoomControlsEnabled = true
            mMap.uiSettings.isMapToolbarEnabled = true
            mMap.uiSettings.isMyLocationButtonEnabled = true
        }
    }

    private fun render(result: PlaceDetailsViewState) {
        Log.e("tartalom1", result.toString())
        when (result) {
            is DownloadPlaceDetailsInProgress -> {
                binding.progressbarPlaceDetails.visibility = View.VISIBLE
            }
            is PlaceDetailsResponseSuccess -> {
                binding.progressbarPlaceDetails.visibility = View.GONE
                processResponse(result.data)
            }
            is PlaceDetailsResponseError -> {
                binding.progressbarPlaceDetails.visibility = View.GONE
                Toast.makeText(
                    this,
                    "HIBA " + result.exceptionMsg,
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    private fun processResponse(
        registrationData: Place
    ) {
        val address =
            registrationData.postalCode + " " + registrationData.city + ", " + registrationData.address
        mMap.apply {
            clear()
            addMarker(MarkerOptions().apply {
                position(LatLng(47.458649, 18.9486852))
                title(registrationData.fullName)
                snippet(address)
            })
            moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(47.458649, 18.9486852), 10f))
        }
        binding.textviewName.text = registrationData.fullName
        binding.textviewAddress.text = address
        binding.textviewCategory.text = registrationData.categories
        binding.textviewDate.text = registrationData.uploadingDate
        binding.textviewUploader.text = registrationData.uploadedBy?.uploaderUserName
        binding.textviewDescription.text = registrationData.description


    }




}