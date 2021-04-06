package com.example.customermanagementsystem.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.ViewModel
import com.example.customermanagementsystem.ViewModelFactory
import com.example.customermanagementsystem.activities.MainActivity
import com.example.customermanagementsystem.models.ClientDTO
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.fragment_client_profile.*

class ClientProfileFragment : Fragment() {

    private lateinit var viewModel: ViewModel
    val args: ClientProfileFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_client_profile, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val prevFragment = args.prevFragment
        if (prevFragment == "fab") {
            edit_btn_client.visibility = View.GONE

        } else if (prevFragment == "bottomSheet") {
            edit_btn_client.visibility = View.VISIBLE
            setView()
        }
        save_btn_client.setOnClickListener {
            if (prevFragment == "fab") {
                createNewUser()
            }
        }
    }

    private fun setView() {
    }

    private fun createNewUser() {

        val surname = surname_client.text.toString()
        val name = name_client.text.toString()
        val patronymic = patronymic_client.text.toString()
        val email = email_client.text.toString()
        val phone = phone_client.text.toString()
        val course = course_client.text.toString()
        val status = "Новый"
        var hasLaptop = false
        radioGroup_clients.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.has_client_rbtn -> hasLaptop = true
                R.id.hasnot_client_rbtn -> hasLaptop = false
            }
        }
        val source = source_client.text.toString()
        val comment = comment_client.text.toString()
        //val newClient = ClientDTO(name, surname, patronymic, email, phone, , hasLaptop, source, comment)
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        //viewModel.createClient( 1, newClient)
        viewModel.myAuthResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.d("AuthD", response.body().toString())
                Log.d("AuthD", response.code().toString())
                Log.d("AuthD", response.message())
            } else if (!response.isSuccessful) {
                Log.d("AuthD", response.body().toString())
                Log.d("AuthD", response.code().toString())
                Log.d("AuthD", response.message())
            }
        })
    }
}