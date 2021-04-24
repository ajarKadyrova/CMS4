package com.example.customermanagementsystem.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.repository.Repository
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_student_add_bottom_sheet.*


class StudentAddBottomSheetFragment : BottomSheetDialogFragment() {

    private var groupId: Int = 0
    private var studentID: Long = 0
    private lateinit var viewModel: ViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val bundle = requireArguments()
        groupId = bundle.getInt("groupID", 0)
        studentID = bundle.getLong("studentID", 0)
        Log.e("studentID", groupId.toString())
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_student_add_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        yes_btn_addStudent.setOnClickListener {
            val repository = Repository()
            val viewModelFactory = ViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
            viewModel.addStudentToGroup(studentID,1, groupId)
            println("AddStudent $studentID")
            println("AddStudent $groupId")
            viewModel.addStudentGroup.observe(viewLifecycleOwner, Observer { response ->
                if(response.isSuccessful){
                    Toast.makeText(context, resources.getString(R.string.add_student_successful), Toast.LENGTH_LONG).show()
                    Log.e("AddStudent", response.code().toString())
                    Log.e("AddStudent", response.message().toString())
                    Log.e("AddStudent", response.body().toString())
                }
                else Toast.makeText(context, resources.getString(R.string.error_occured), Toast.LENGTH_LONG).show()
                Log.e("AddStudent", response.code().toString())
                Log.e("AddStudent", response.message().toString())
                Log.e("AddStudent", response.errorBody().toString())
            })
        }
        no_btn_addStudent.setOnClickListener {
            findNavController().navigate(R.id.action_studentAddBottomSheetFragment_to_groupAddStudentFragment)
        }
    }
}