package com.example.customermanagementsystem.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.customermanagementsystem.R
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        val successAlertDialog = AlertDialog.Builder(this)
            .setMessage(resources.getString(R.string.success_password_alert))
            .setIcon(R.drawable.success_sign)
            .setNeutralButton("Ок"){ _, _ ->
                Toast.makeText(this, "Toast", Toast.LENGTH_SHORT).show()
            }.create()

        sendLink_btn.setOnClickListener {
            successAlertDialog.show()
        }
    }
}