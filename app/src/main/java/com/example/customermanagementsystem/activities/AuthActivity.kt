package com.example.customermanagementsystem.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.customermanagementsystem.*
import com.example.customermanagementsystem.models.AuthUser
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        signIn_btn.setOnClickListener {
            signIn_btn.visibility = View.INVISIBLE
            progress_bar_auth.visibility = View.VISIBLE
            login_auth.setText("ajar.kadyrova01@gmail.com")
            password_auth.setText("neolabs1")
            val login =  login_auth.text.toString()
            val password = password_auth.text.toString()
            val authUser = AuthUser(login, password)
            val repository = Repository()
            val viewModelFactory = ViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
            viewModel.authUser(authUser)
            viewModel.myAuthResponse.observe(this, Observer { response ->
                if(response.isSuccessful){
                    Log.d("AuthD", response.body().toString())
                    Log.d("AuthD", response.code().toString())
                    Log.d("AuthD", response.message())
                    val sharedPreferences : SharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE)
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.apply {
                        putString("token", response.body()?.accessToken)
                    }.apply()
                    Intent(this, MainActivity::class.java).also {
                        startActivity(it)
                    }
                }
                else if(!response.isSuccessful){
                    Log.d("AuthD", response.body().toString())
                    Log.d("AuthD", response.code().toString())
                    Log.d("AuthD", response.message())
                }
            })
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
    }
}