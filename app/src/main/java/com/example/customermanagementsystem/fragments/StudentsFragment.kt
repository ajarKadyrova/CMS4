package com.example.customermanagementsystem.fragments

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
import com.example.customermanagementsystem.adapter.StudentsAdapter
import com.example.customermanagementsystem.models.StudentsDTO
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.fragment_students.*

class StudentsFragment : Fragment(), StudentsAdapter.OnItemClickListener {

    private lateinit var viewModel: ViewModel
    private var studentsList: List<StudentsDTO> = ArrayList()
    private val adapter by lazy { StudentsAdapter(studentsList, this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_students, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getAllStudents(1)
        viewModel.allStudents.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { adapter.setData(it) }
                recyclerView_students.adapter = adapter
                recyclerView_students.layoutManager = LinearLayoutManager(context)
                response.body()?.let { adapter.setData(it) }
            } else {
                Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                Log.d("Students", "body + " + response.body().toString())
            }
        })
    }

    override fun onItemClick(position: Int) {
        val clickedItem: StudentsDTO = studentsList[position]
        adapter.notifyItemChanged(position)
        val bundle = Bundle()
        val id: Long = studentsList[position].id
        //val board: String = clientsList[position].boards.boardName
        bundle.putLong("studentID", id)
        bundle.putSerializable("student", studentsList[position])
        //bundle.putString("clientBoard", board)
        Log.e("studentID: ", id.toString())
        findNavController().navigate(R.id.action_mainFragment_to_bottomSheetFragment, bundle)
    }
}