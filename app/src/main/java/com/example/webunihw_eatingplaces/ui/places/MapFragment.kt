package com.example.webunihw_eatingplaces.ui.places

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.webunihw_eatingplaces.R
import com.example.webunihw_eatingplaces.databinding.FragmentMapBinding
import com.example.webunihw_eatingplaces.model.places.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var viewModel: PlaceListViewModel
    private val placesMarkerList = mutableListOf<Place>()
    private var _binding: FragmentMapBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        return binding.root


    }//ONCREATEVIEW -----------------------------------------------------------------------------

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFrg) as SupportMapFragment?
        if (mapFragment != null) {
            mapFragment.onCreate(savedInstanceState)
            mapFragment.getMapAsync(this)
        }


    }//ONVIEWCREATED -----------------------------------------------------------------------------


    override fun onMapReady(googleMap: GoogleMap?) {
        googleMap?.let {
            mMap = googleMap
            mMap.uiSettings.isCompassEnabled = true
            mMap.uiSettings.isZoomControlsEnabled = true
            mMap.uiSettings.isMapToolbarEnabled = true
            mMap.uiSettings.isMyLocationButtonEnabled = true

            var budapest = LatLng(47.458649, 18.9486852)
            mMap.addMarker(MarkerOptions().position(budapest).title("Marker in Hun"))
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(budapest, 10f))
            //még egy
            budapest = LatLng(47.458649, 19.9486852)
            mMap.addMarker(MarkerOptions().position(budapest).title("Marker in Budapest"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(budapest))
        }
    }


    @SuppressLint("MissingPermission")
    private fun validatePermissions() {

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
            mMap.setOnMyLocationButtonClickListener { goToMyLocation() }
            goToMyLocation()
        } else {
            requestLocationPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun goToMyLocation(): Boolean {
        context?.let {
            val locationManager = it.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (location != null) {
                mMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        LatLng(
                            location.latitude,
                            location.longitude
                        ), 14.0f
                    )
                )
            }
        }
        return true
    }


    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            activity as Activity,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ), 101
        )
    }

    companion object {
        fun newInstance() = MapFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}