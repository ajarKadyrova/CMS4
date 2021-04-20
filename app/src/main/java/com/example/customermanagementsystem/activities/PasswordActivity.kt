package com.example.customermanagementsystem.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.fragments.StudentsFragment
import com.example.customermanagementsystem.repository.Repository
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
import kotlinx.android.synthetic.main.activity_password.*
import kotlinx.android.synthetic.main.fragment_wrap_students.*

class PasswordActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        val successAlertDialog = AlertDialog.Builder(this)
            .setMessage(resources.getString(R.string.success_password_alert))
            .setIcon(R.drawable.success_sign)
            .setNeutralButton("Ок"){ _, _ ->
            }.create()

        sendLink_btn.setOnClickListener {
            val repository = Repository()
            val viewModelFactory = ViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)

            val email = email_reset.text.toString()
            viewModel.getRecoveryPassword(email)
            viewModel.myEmail.observe(this, Observer { response ->
                if (response.isSuccessful) {
                    successAlertDialog.show()
                    Log.d("RESETPASSWORD", response.body().toString())
                    Log.d("RESETPASSWORD", response.code().toString())
                } else if (!response.isSuccessful) {
                    Toast.makeText(applicationContext, resources.getString(R.string.error_occured), Toast.LENGTH_LONG).show()
                    Log.d("RESETPASSWORD", response.body().toString())
                    Log.d("RESETPASSWORD", response.code().toString())
                }
            })

        }
    }
}