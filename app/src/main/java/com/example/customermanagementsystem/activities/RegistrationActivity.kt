package com.example.customermanagementsystem

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.customermanagementsystem.models.AuthUserResult
import com.example.customermanagementsystem.models.RegistrationModel
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.alertdialog_card.view.*
import kotlinx.android.synthetic.main.registration_activity.*

internal class RegistrationActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        val dialogView = View.inflate(this, R.layout.alertdialog_card, null)
        val successAlertDialog = AlertDialog.Builder(this).setView(dialogView).create()
        dialogView.message_alert.text = getString(R.string.registration_alert)
        register_btn.setOnClickListener {
            val surname = surname_register.text.toString()
            val name =  name_register.text.toString()
            val email = email_register.text.toString()
            val password1 = password_register.text.toString()
            val password2 = password2_register.text.toString()
            if(password_register.text != password2_register.text && password_register.length() < 6 || password2_register.length() < 6){
                password_register.setError(R.string.password_alert.toString())
                password2_register.setError(R.string.password_alert.toString())
            }
            val myUser = RegistrationModel( password2,email, name, surname, password1)
            val repository = Repository()
            val viewModelFactory = RegisterViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
            viewModel.registerUser(myUser)
            viewModel.myResponse.observe(this, Observer { response ->
                if(response.isSuccessful){
                    Log.d("RegistrationD", response.body().toString())
                    Log.d("RegistrationD", response.code().toString())
                    Log.d("RegistrationD", response.message())
                    successAlertDialog.show()
                }
                else if(!response.isSuccessful){
                    Log.d("RegistrationD", response.body().toString())
                    Log.d("RegistrationD", response.code().toString())
                    Log.d("RegistrationD", response.message())
                }
            })
        }
        dialogView.btn_ok.setOnClickListener {
            successAlertDialog.dismiss()
        }
    }
}