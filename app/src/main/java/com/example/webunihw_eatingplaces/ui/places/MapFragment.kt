package com.example.webunihw_eatingplaces.ui.places

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.webunihw_eatingplaces.R
import com.example.webunihw_eatingplaces.databinding.FragmentMapBinding
import com.example.webunihw_eatingplaces.model.PlaceListResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback {

    val placelistViewModel by viewModels<PlaceListViewModel>()
    private lateinit var mMap: GoogleMap
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

    override fun onResume() {
        super.onResume()
        placelistViewModel.getPlaces()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        placelistViewModel.getPlacesLiveData()
            .observe(viewLifecycleOwner, { placeListResult -> render(placeListResult) })

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFrg) as SupportMapFragment?
        if (mapFragment != null) {
            mapFragment.onCreate(savedInstanceState)
            mapFragment.getMapAsync(this)
        }


    }//ONVIEWCREATED -----------------------------------------------------------------------------


    private fun render(result: PlaceListViewState) {
        Log.e("tartalom", result.toString())
        when (result) {
            is DownloadPlacesInProgress -> {
                binding.progressbarMap.visibility = View.VISIBLE
            }
            is PlaceListResponseSuccess -> {
                binding.progressbarMap.visibility = View.GONE
                processResponse(result.data)
                Log.e("tartalom", result.toString())
            }
            is PlaceListResponseError -> {
                binding.progressbarMap.visibility = View.GONE
                Toast.makeText(
                    //TODO mi az a requireContext?
                    requireContext(),
                    "HIBA" + result.exceptionMsg,
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }


    private fun processResponse(placelistData: PlaceListResult?) {
        if (placelistData != null) {
            mMap.apply {
                clear()
                placelistData.places?.forEach {
                    addMarker(MarkerOptions().apply {
                        position(LatLng(it.coord.lat, it.coord.lon))
                        title(it.fullName)
                        snippet(it.city)
                    })
                }
                //focus on user's location!
                goToMyLocation()
            }
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
                        ), 12.0f
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