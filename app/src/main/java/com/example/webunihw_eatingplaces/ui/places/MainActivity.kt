package com.example.webunihw_eatingplaces.ui.places

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.webunihw_eatingplaces.R
import com.example.webunihw_eatingplaces.databinding.ActivityMainBinding
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pageNames: Array<String>
    private var pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            Toast.makeText(
                this@MainActivity, "Selected position: ${position}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pageNames = resources.getStringArray(R.array.tab_names)

//TODO beállítani, hogy csak eng birtokában töltődjön be
        requestNeededPermission()

        val fragmentStatePagerAdapter = MyFragmentStatePagerAdapter(this, 2)
        binding.mainViewPager.adapter = fragmentStatePagerAdapter

        binding.mainViewPager.registerOnPageChangeCallback(pageChangeCallback)
        //swipe enable - because of map handling
        binding.mainViewPager.isUserInputEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.mainViewPager) { tab, position ->
            tab.text = pageNames[position]
        }.attach()


        //adott indexű tab megnyitása extrában kapott adat alapján
//        val defaultValue = 0
//        val page = intent.getIntExtra("PAGE", defaultValue)
//        binding.mainViewPager.setCurrentItem(page)

    }//ONCREATE

    fun requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),101)
        } else {
            // we have the permission
            checkGlobalLocationSettings()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            101 -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "ACCESS_FINE_LOCATION perm granted", Toast.LENGTH_SHORT)
                        .show()

                    //handleLocationStart()
                } else {
                    Toast.makeText(
                        this,
                        "ACCESS_FINE_LOCATION perm NOT granted", Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }

    fun checkGlobalLocationSettings() {
        val locationRequest = LocationRequest.create()?.apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest!!)

        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())


        task.addOnSuccessListener { locationSettingsResponse ->
            Toast.makeText(
                this,
                "Location enabled: ${locationSettingsResponse.locationSettingsStates.isLocationUsable}",
                Toast.LENGTH_LONG
            ).show()
        }
    }


    override fun onDestroy() {
        binding.mainViewPager.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroy()
    }
}