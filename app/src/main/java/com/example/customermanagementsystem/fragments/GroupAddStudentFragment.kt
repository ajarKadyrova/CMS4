package com.example.customermanagementsystem.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.repository.ViewModel

class GroupAddStudentFragment: Fragment() {

    private lateinit var viewModel: ViewModel
    private var groupId:Long = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_students, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        groupId = (parentFragment as WrapGroupDataFragment).groupId
        Log.e("groupStudentsID", groupId.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}