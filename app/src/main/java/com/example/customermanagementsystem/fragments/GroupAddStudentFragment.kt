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
import com.example.customermanagementsystem.adapter.StudentsAdapter
import com.example.customermanagementsystem.models.StudentsDTO
import com.example.customermanagementsystem.repository.Repository
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_students.*

class GroupAddStudentFragment: Fragment(), StudentsAdapter.OnItemClickListener {

    private lateinit var viewModel: ViewModel
    private var groupId:Long = 0
    private var studentsList: List<StudentsDTO> = ArrayList()
    val adapter by lazy{ StudentsAdapter(studentsList, this) }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_students, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        groupId = requireArguments().getLong("groupId", 0)
        Log.e("groupStudentsID", groupId.toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository
        )
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getStudentsWoutGroups(1)
        viewModel.studentWoutGroup.observe(viewLifecycleOwner, Observer { response ->
            if(response.isSuccessful) {
                studentsList = response.body()!!
                if (studentsList.isEmpty()){
                    Log.e("No students", 0.toString())
                    textView_students.visibility = View.VISIBLE
                }
                else{
                    textView_students.visibility = View.GONE
                    adapter.setData(studentsList)
                    recyclerView_students.layoutManager = LinearLayoutManager(this.context)
                    recyclerView_students.adapter = adapter
                }
            }
            else Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_SHORT).show()
        })

    }

    override fun onItemClick(position: Int) {
        val clickedItem: Long = studentsList[position].id
        adapter.notifyItemChanged(position)
        Toast.makeText(context, clickedItem.toString(), Toast.LENGTH_LONG).show()
        var bundle = Bundle()
        bundle.putLong("studentID", clickedItem)
        bundle.putSerializable("studentBody", studentsList[position])
        findNavController().navigate(R.id.action_groupAddStudentFragment_to_studentAddBottomSheetFragment, bundle)
    }
}