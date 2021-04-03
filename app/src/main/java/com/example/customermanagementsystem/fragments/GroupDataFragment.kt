package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.customermanagementsystem.R
import kotlinx.android.synthetic.main.fragment_group_data.*
import java.util.*

class GroupDataFragment : Fragment() {

    val argument: GroupDataFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_group_data, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        //start_date_groups_tv

        val prevFragment = argument.prevFragmentGroup
        if(prevFragment == "newGroup"){
            edit_btn_groups.setText(resources.getString(R.string.save))
            archive_btn_groups.visibility = View.GONE
        }
        else {
            edit_btn_groups.setText(resources.getString(R.string.edit))
            archive_btn_groups.visibility = View.VISIBLE
       }
    }
}