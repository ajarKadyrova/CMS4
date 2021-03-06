package com.example.customermanagementsystem.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
import com.example.customermanagementsystem.adapter.GroupStudentsAdapter
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.fragment_group_students.*

class GroupStudentsFragment : Fragment() {

    private lateinit var viewModel: ViewModel
    private var groupId:Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_group_students, container, false)
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        groupId = (parentFragment as WrapGroupDataFragment).groupId.toInt()
        Log.e("groupStudentsID", groupId.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getGroup(1, groupId)
        viewModel.myGroup.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful) {
                val studentsList = response.body()!!
                if (response.body()!!.numberOfStudents == 0){
                    Log.e("No students", 0.toString())
                    student_name_tv.visibility = View.GONE
                    date_tv1.visibility = View.GONE
                    date_tv2.visibility = View.GONE
                    date_tv3.visibility = View.GONE
                    textView_students_group.visibility = View.VISIBLE
                }
                else{
                    val adapter by lazy{ GroupStudentsAdapter(studentsList)}
                    textView_students_group.visibility = View.GONE
                    adapter.setData(studentsList)
                    recyclerView_group_students.layoutManager = LinearLayoutManager(this.context)
                    recyclerView_group_students.adapter = adapter
                }
            }
            else Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_SHORT).show()
        })

        fab_add_student.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("groupId", groupId)
            findNavController().navigate(R.id.action_wrapGroupDataFragment_to_groupAddStudentFragment, bundle)
        }
    }
}