package com.example.webunihw_eatingplaces.ui.places

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.webunihw_eatingplaces.R
import com.example.webunihw_eatingplaces.databinding.ActivityMainBinding
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

        val fragmentStatePagerAdapter = MyFragmentStatePagerAdapter(this, 2)
        binding.mainViewPager.adapter = fragmentStatePagerAdapter

        binding.mainViewPager.registerOnPageChangeCallback(pageChangeCallback)

        TabLayoutMediator(binding.tabLayout, binding.mainViewPager) { tab, position ->
            tab.text = pageNames[position]
        }.attach()

        //adott indexű tab megnyitása extrában kapott adat alapján
//        val defaultValue = 0
//        val page = intent.getIntExtra("PAGE", defaultValue)
//        binding.mainViewPager.setCurrentItem(page)

    }//ONCREATE

    override fun onDestroy() {
        binding.mainViewPager.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroy()
    }
}