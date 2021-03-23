package com.example.customermanagementsystem

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.customermanagementsystem.models.RegistrationModel
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.alertdialog_card.*
import kotlinx.android.synthetic.main.alertdialog_card.view.*
import kotlinx.android.synthetic.main.registration_activity.*

internal class RegistrationActivity : AppCompatActivity() {

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)

        val repository = Repository()
        val viewModelFactory = RegisterViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)
        val myUser = RegistrationModel("neolabs1", "neolabs@gmail.com", "Neo", "Labs", "neolabs1")
        viewModel.registerUser(myUser)
        viewModel.myResponse.observe(this, Observer { response ->
            if(response.isSuccessful){
                Log.d("RegistrationD", response.body().toString())
                Log.d("RegistrationD", response.code().toString())
                Log.d("RegistrationD", response.message())
                    }
        })

        val dialogView = View.inflate(this, R.layout.alertdialog_card, null)
        val successAlertDialog = AlertDialog.Builder(this).setView(dialogView).create()
        dialogView.message_alert.text = getString(R.string.registration_alert)
        register_btn.setOnClickListener {
            if(password_otf.text != password2_otf.text && password_otf.length() < 6 || password2_otf.length() < 6){
                password_otf.setError(R.string.password_alert.toString())
                password2_otf.setError(R.string.password_alert.toString())
            }
            else successAlertDialog.show()
        }
        dialogView.btn_ok.setOnClickListener {
            successAlertDialog.dismiss()
        }
    }
}