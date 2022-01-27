package com.example.webunihw_eatingplaces.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.webunihw_eatingplaces.databinding.ActivityLoginBinding
import com.example.webunihw_eatingplaces.model.auth.LoginResult
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.getLoginLiveData()
            .observe(this, { weatherDetailsResult -> render(weatherDetailsResult) })
       // loginViewModel.login(binding.edittextUsername.text.toString(),binding.edittextPassword.text.toString())
        binding.buttonLogin.setOnClickListener() {
            loginViewModel.login("hardvera@gmail.com", "mmmmmmmm")
        }

    }//ONCREATE

    private fun render(result: LoginViewState) {
        Log.e("tartalom", result.toString())
        when (result) {
            is InProgress -> {
                binding.progressbarLogin.visibility = View.VISIBLE
            }
            is LoginResponseSuccess -> {
                binding.progressbarLogin.visibility = View.GONE
                processResponse(result.data)
                Log.e("tartalom", result.toString())
            }
            is LoginResponseError -> {
                binding.progressbarLogin.visibility = View.GONE
                Toast.makeText(
                    this,
                    "HIBA"+result.exceptionMsg,
                    Toast.LENGTH_SHORT
                ).show()

            }
        }
    }

    private fun processResponse(
        loginData: LoginResult?
    ) {
        if (loginData != null) {
            Log.e("hiba", loginData.userId.toString())
        }
        val intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent)
        this.finish()
    }

}