package com.example.customermanagementsystem.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
import com.example.customermanagementsystem.models.AuthUser
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.activity_auth.*


class AuthActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        password_auth.transformationMethod = PasswordTransformationMethod.getInstance()
        signIn_btn.visibility = View.VISIBLE
        progress_bar_auth.visibility = View.INVISIBLE

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
        signIn_btn.setOnClickListener {
            checkConnection()
        }
    }

    private fun checkConnection() {
        val connectManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            //checkAuthorization()
            authorizeUser()
        } else {
            Toast.makeText(applicationContext, resources.getString(R.string.no_internet), Toast.LENGTH_LONG).show()
        }
    }

    private fun checkAuthorization() {
        TODO("Not yet implemented")
    }

    private fun authorizeUser() {
        signIn_btn.visibility = View.INVISIBLE
        progress_bar_auth.visibility = View.VISIBLE
        login_auth.setText("ajar.kadyrova01@gmail.com")
        password_auth.setText("neolabs1")
        val login = login_auth.text.toString()
        val password = password_auth.text.toString()
        val authUser = AuthUser(login, password)
        val repository = Repository()
        val viewModelFactory =
            ViewModelFactory(
                repository
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.authUser(authUser)
        viewModel.myAuthResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d("AuthD", response.body().toString())
                Log.d("AuthD", response.code().toString())
                Log.d("AuthD", response.message())
                val sharedPreferences: SharedPreferences =
                    getSharedPreferences("token", Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.apply {
                    putString("token", response.body()?.accessToken)
                }.apply()
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            } else if (!response.isSuccessful) {
                Log.d("AuthD", response.body().toString())
                Log.d("AuthD", response.code().toString())
                Log.d("AuthD", response.message())
                Toast.makeText(applicationContext, resources.getString(R.string.error_occured), Toast.LENGTH_LONG).show()
            }
        })
    }
}