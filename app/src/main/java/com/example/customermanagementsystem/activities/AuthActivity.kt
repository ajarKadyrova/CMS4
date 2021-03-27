package com.example.customermanagementsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.customermanagementsystem.models.AuthUser
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        signIn_btn.setOnClickListener {
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
            }
        }
        signUp_btn.setOnClickListener {
            Intent(this, RegistrationActivity::class.java).also {
                startActivity(it)
            }
        }
        forgotPassword_btn.setOnClickListener {
            Intent(this, PasswordActivity::class.java).also {
                startActivity(it)
            }
        }
        val login =  login_auth.text.toString()
        val password = password_auth.text.toString()
        val authUser = AuthUser(login, password)
        val repository = Repository()
        val viewModelFactory = RegisterViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
        viewModel.authUser(authUser)
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful){
                Log.d("AuthD", response.body().toString())
                Log.d("AuthD", response.code().toString())
                Log.d("AuthD", response.message())
            }
            else if(!response.isSuccessful){
                Log.d("AuthD", response.body().toString())
                Log.d("AuthD", response.code().toString())
                Log.d("AuthD", response.message())
            }
        })
    }
}