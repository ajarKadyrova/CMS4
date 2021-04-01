package com.example.customermanagementsystem.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.customermanagementsystem.R
import kotlinx.android.synthetic.main.fragment_client_profile.*

class ClientProfileFragment : Fragment() {

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
        if(prevFragment == "fab"){
            edit_btn_client.visibility = View.GONE
        }
        /*save_btn_client.setOnClickListener {
            if(prevFragment == "fab"){
                val surname = surname_client.text.toString()
                val name = name_client.text.toString()
                val patronymic = patronymic_client.text.toString()
                val email = email_client.text.toString()
                val phone = phone_client.text.toString()
                val teacher = teacher_client.text.toString()
                val course = course_client.text.toString()
            }
        }*/
    }
}