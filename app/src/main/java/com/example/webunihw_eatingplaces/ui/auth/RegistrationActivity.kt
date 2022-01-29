package com.example.webunihw_eatingplaces.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.webunihw_eatingplaces.databinding.ActivityRegistrationBinding
import com.example.webunihw_eatingplaces.ui.places.MainActivity
import okhttp3.ResponseBody

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding
    private val registrationViewModel: RegistrationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrationViewModel.getRegistrationLiveData()
            .observe(this, { registrationDetailsResult -> render(registrationDetailsResult) })

        binding.buttonRegistration.setOnClickListener() {
            if(isFilledForm()) {
                registrationViewModel.registration(
                    binding.edittextEmail.text.toString(),
                    binding.edittextUsername.text.toString(),
                    binding.edittextPassword.text.toString()
                )
            }
        }

    }//ONCREATE


    private fun render(result: RegistrationViewState) {
        Log.e("tartalom1", result.toString())
        when (result) {
            is RegistrationInProgress -> {
                binding.progressbarRegistration.visibility = View.VISIBLE
            }
            is RegistrationResponseSuccess -> {
                binding.progressbarRegistration.visibility = View.GONE
                processResponse(result.data)
                Log.e("tartalom2", result.toString())
            }
            is RegistrationResponseError -> {
                binding.progressbarRegistration.visibility = View.GONE
                Toast.makeText(
                    this,
                    "HIBA" + result.exceptionMsg,
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

    private fun isFilledForm(): Boolean {
        if (!isValidEmail(binding.edittextEmail.text.toString())) {
            Toast.makeText(
                this,
                "Kérjük, ellenőrizd email címed helyességét!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (binding.edittextUsername.text.toString().equals("")) {
            Toast.makeText(
                this,
                "Kérjük, adj meg egy felhasználónevet!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (binding.edittextPassword.text.toString().equals("")) {
            Toast.makeText(
                this,
                "Kérjük, add meg a jelszavadat!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (binding.edittextPassword.length() < 8) {
            Toast.makeText(
                this,
                "A megadott jelszó rövidebb, mint 8 karakter" + binding.edittextPassword.length(),
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (binding.edittextPasswordAgain.text.toString().equals("")) {
            Toast.makeText(
                this,
                "Kérjük, add meg a jelszavadat még egyszer!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        } else if (!(binding.edittextPasswordAgain.text.toString()
                .equals(binding.edittextPassword.text.toString()))
        ) {
            Toast.makeText(
                this,
                "A megadott jelszavak nem egyeznek!",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}