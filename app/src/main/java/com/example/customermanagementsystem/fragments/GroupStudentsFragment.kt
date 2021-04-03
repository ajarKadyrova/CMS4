package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.adapter.GroupStudentsAdapter
import com.example.customermanagementsystem.models.GroupStudents
import kotlinx.android.synthetic.main.fragment_group_students.*

class GroupStudentsFragment : Fragment() {

    private val list1 = ArrayList<GroupStudents>()
    private val adapter = GroupStudentsAdapter(list1)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_group_students, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = generateData(15)
        recyclerView_group_students.adapter = adapter
        recyclerView_group_students.layoutManager = LinearLayoutManager(context)
        recyclerView_group_students.setHasFixedSize(true)

        fab_add_student.setOnClickListener {
            findNavController().navigate(R.id.action_wrapGroupDataFragment_to_studentAddBottomSheetFragment)
        }
    }

    private fun generateData(size: Int): List <GroupStudents> {
        for (i in 0 until size){
            val item = GroupStudents(
                "Азимбаев \nДинмухаммед",
                9000,
                900,
                0
            )
            list1 += item
        }
        return list1;
    }
}