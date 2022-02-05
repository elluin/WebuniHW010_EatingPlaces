package com.example.webunihw_eatingplaces.ui.places.upload

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.webunihw_eatingplaces.databinding.ActivityUploadplaceBinding
import com.example.webunihw_eatingplaces.ui.places.MainActivity
import okhttp3.ResponseBody

class UploadPlaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadplaceBinding
    private val uploadViewModel: UploadPlaceViewModel by viewModels()
    val REQUEST_CODE = 100
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadplaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            actionBar.setDisplayShowTitleEnabled(true)
            actionBar.setTitle("Hely feltöltése")
        }

        uploadViewModel.getUploadPlaceLiveData()
            .observe(this, { uploadResult -> render(uploadResult) })

        binding.buttonSelectPlaceImage.setOnClickListener() {
            openGalleryForImage(REQUEST_CODE)
        }

        binding.buttonUploadPlace.setOnClickListener() {
            var lat = "47.0"
            var lon = "19.5"
            var image = ""
            var categories: String = ""
            if (binding.checkBoxGlutenfree.isChecked) categories += "gluténmentes "
            if (binding.checkBoxLactosefree.isChecked) categories += "laktózmentes "
            if (binding.checkBoxVegan.isChecked) categories += "vegán "
            if (binding.checkBoxVegetarian.isChecked) categories += "vegetáriánus "
            Log.d("kategória", categories)

            uploadViewModel.upload(
                binding.edittextPlaceFullname.text.toString(),
                binding.edittextPlacePostalcode.text.toString(),
                binding.edittextPlaceCity.text.toString(),
                binding.edittextPlaceStreet.text.toString(),
                lat,
                lon,
                categories,
                image,
                binding.edittextDescription.text.toString()
            )

        }


    }//ONCREATE

    private fun render(result: UploadPlaceViewState) {
        Log.e("tartalom1", result.toString())
        when (result) {
            is UploadInProgress -> {
                binding.progressbarUpload.visibility = View.VISIBLE
            }
            is UploadPlaceResponseSuccess -> {
                binding.progressbarUpload.visibility = View.GONE
                processResponse(result.data)
            }
            is UploadPlaceResponseError -> {
                binding.progressbarUpload.visibility = View.GONE
                Toast.makeText(
                    this,
                    "HIBA " + result.exceptionMsg,
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    private fun processResponse(
        registrationData: ResponseBody?
    ) {
        if (registrationData != null) {
            Log.e("hiba", registrationData.toString())
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    private fun openGalleryForImage(requestCode: Int) {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            imageUri = data!!.data!!
            binding.imageviewPlaceimage.setImageURI(imageUri) // handle chosen image


        }

    }
}