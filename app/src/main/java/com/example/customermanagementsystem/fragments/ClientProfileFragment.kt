package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
import com.example.customermanagementsystem.models.*
import com.example.customermanagementsystem.repository.Repository
import kotlinx.android.synthetic.main.fragment_client_profile.*

class ClientProfileFragment : Fragment() {

    private lateinit var viewModel: ViewModel
    val args: ClientProfileFragmentArgs by navArgs()
    private var coursesList: List<CourseDTO> = ArrayList()
    private var boardsList: List<ClientDTO> = ArrayList()
    private lateinit var coursesHashMap: HashMap<String, Long>
    private lateinit var boardsHashMap: HashMap<String, Long>

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
            setView()
        } else if (prevFragment == "bottomSheet") {
            edit_btn_client.visibility = View.VISIBLE
            setViewOfClient()
        }
        save_btn_client.setOnClickListener {
            if (prevFragment == "fab") {
                createNewUser()
            }
        }
    }

    private fun setView() {
        val repository = Repository()
        val viewModelFactory =
            ViewModelFactory(
                repository
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)

        viewModel.getAllCourses()
        viewModel.allCourses.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                coursesList = response.body()!!
                val courses: MutableList<String> = ArrayList()
                for (i in coursesList.indices) {
                    courses.add(coursesList[i].name)
                }
                val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, courses)
                course_client.setAdapter(adapter)
                for (i in coursesList.indices) {
                    coursesHashMap = HashMap()
                    coursesHashMap[coursesList[i].name] = coursesList[i].id

                }
                //course_group.setText(coursesList[0].name)
            } else {
                Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                Log.d("Client", "body + " + response.body().toString())
            }
        })

        viewModel.getAllBoards(1)
        viewModel.allBoards.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                boardsList = response.body()!!
                val boards: MutableList<String> = ArrayList()
                for (i in boardsList.indices) {
                    boards.add(boardsList[i].boardName)
                }
                val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, boards)
                status_client.setAdapter(adapter)
                for (i in boardsList.indices) {
                    boardsHashMap = HashMap()
                    boardsHashMap[boardsList[i].boardName] = boardsList[i].id

                }
                //course_group.setText(coursesList[0].name)
            } else {
                Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                Log.d("BOARDSHASH", "body + " + response.body().toString())
            }
        })
    }

    private fun setViewOfClient() {
    }

    private fun createNewUser() {
        val surname = surname_client.text.toString()
        val name = name_client.text.toString()
        val patronymic = patronymic_client.text.toString()
        val email = email_client.text.toString()
        val phone = phone_client.text.toString()
        val status = "Новый"
        var hasLaptop = false
        radioGroup_clients.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.has_client_rbtn -> hasLaptop = true
                R.id.hasnot_client_rbtn -> hasLaptop = false
            }
        }
        val source = source_client.text.toString()
        val comment = comment_client.text.toString()
        val board = BoardID(1)
        val wantCourse = BoardID(1)
        val newClient = PostClient(board, name, surname, patronymic, email, phone, hasLaptop, wantCourse, source, comment)
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.createClient( 1, newClient)
        viewModel.newClientResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.d("NewClient", response.body().toString())
                Log.d("NewClient", response.code().toString())
                Log.d("NewClient", response.message())
            } else if (!response.isSuccessful) {
                Log.d("NewClient", response.body().toString())
                Log.d("NewClient", response.code().toString())
                Log.d("NewClient", response.message())
            }
        })
    }
}