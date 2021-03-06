package com.example.webunihw_eatingplaces.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.webunihw_eatingplaces.database.AppDatabase
import com.example.webunihw_eatingplaces.databinding.ActivityLoginBinding
import com.example.webunihw_eatingplaces.model.auth.LoginResult
import com.example.webunihw_eatingplaces.model.db.User
import com.example.webunihw_eatingplaces.ui.places.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel.getLoginLiveData()
            .observe(this, { loginDetailsResult -> render(loginDetailsResult) })

        binding.buttonLogin.setOnClickListener() {
            loginViewModel.login(binding.edittextUsername.text.toString(), binding.edittextPassword.text.toString())
            Log.e("login adatok", binding.edittextUsername.text.toString())
        }

        binding.buttonReg.setOnClickListener() {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }


    }//ONCREATE

    private fun render(result: LoginViewState) {
        Log.e("tartalom", result.toString())
        when (result) {
            is LoginInProgress -> {
                binding.progressbarLogin.visibility = View.VISIBLE
            }
            is LoginResponseSuccess -> {
                binding.progressbarLogin.visibility = View.GONE
                processResponse(result.data)
                loginViewModel.insert(User(null,result.data.userId,result.data.userEmail, result.data.userName,result.data.userToken))
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

}