package com.example.customermanagementsystem.fragments

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.ViewModel
import com.example.customermanagementsystem.ViewModelFactory
import com.example.customermanagementsystem.models.GroupDTO
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.fragment_group_data.*
import java.util.*

class GroupDataFragment : Fragment() {

    private lateinit var viewModel: ViewModel
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
        else if(argument.groupId > 0){
            val groupId = argument.groupId
            edit_btn_groups.setText(resources.getString(R.string.edit))
            archive_btn_groups.visibility = View.VISIBLE

            val repository = Repository()
            val viewModelFactory = ViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
            viewModel.getGroup(1, groupId)
            viewModel.myGroup.observe(viewLifecycleOwner, Observer { response ->
                if(response.isSuccessful) {
                    val myGroup = response.body()
                    if (myGroup != null) {
                        setView(myGroup)
                    }
                }
                else Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_SHORT).show()
            })

       }
    }

    private fun setView(myGroup:GroupDTO) {
        course_group.setText(myGroup.course.name)
        group_group.setText(myGroup.groupName)
        teacher_group.setText(myGroup.teacher.name)
        schedule_group.setText(myGroup.timeTable.daysOfWeeks.toString())
        time_group.setText(myGroup.timeTable.startTime)
        start_date_groups.setText(myGroup.startDate)
        end_date_groups.setText(myGroup.endDate)
        payment_groups.setText(myGroup.amount.toString())
        cabinet_group.setText(myGroup.timeTable.room.roomName)
    }
}