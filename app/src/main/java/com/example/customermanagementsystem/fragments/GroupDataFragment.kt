package com.example.customermanagementsystem.fragments


import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.ViewModel
import com.example.customermanagementsystem.ViewModelFactory
import com.example.customermanagementsystem.models.CourseDTO
import com.example.customermanagementsystem.models.GroupDTO
import com.example.customermanagementsystem.models.RoomDTO
import com.example.customermanagementsystem.models.TeacherDTO
import com.example.customermanagementsystem.repository.Repository
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_group_data.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class GroupDataFragment : Fragment() {

    private lateinit var viewModel: ViewModel
    private var teachersList: List<TeacherDTO> = ArrayList()
    private var roomsList: List<RoomDTO> = ArrayList()
    private var coursesList: List<CourseDTO> = ArrayList()
    private lateinit var teachersHashMap: HashMap<String, Long>
    private lateinit var roomsHashMap: HashMap<String, Long>
    private lateinit var coursesHashMap: HashMap<String, Long>
    private var groupId: Long = 0
    var calendar = Calendar.getInstance()
    var fGroupDate = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_group_data, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        groupId = sharedPreferences.getLong("groupId", 0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //val groupFragment = GroupsFragment()
        //groupId = groupFragment.getInstanceGroup()?.getGroupId()!!

        if (groupId <= 0) {
            edit_btn_groups.setText(resources.getString(R.string.save))
            archive_btn_groups.visibility = View.GONE
            setView()
        } else if (groupId > 0) {
            edit_btn_groups.setText(resources.getString(R.string.edit))
            archive_btn_groups.visibility = View.VISIBLE

            val repository = Repository()
            val viewModelFactory = ViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
            viewModel.getGroup(1, groupId)
            viewModel.myGroup.observe(viewLifecycleOwner, Observer { response ->
                if (response.isSuccessful) {
                    val myGroup = response.body()
                    if (myGroup != null) {
                        setViewGroup(myGroup)
                    }
                } else Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_SHORT).show()
            })
        }
        edit_btn_groups.setOnClickListener {
            if (edit_btn_groups.text == resources.getString(R.string.save)) {
                getData()
            }
        }

        start_date_groups_til.setEndIconOnClickListener {
            pickDate()
            fGroupDate = 0
        }

        end_date_groups_til.setEndIconOnClickListener {
            pickDate()
            fGroupDate = 1
        }

        time_group_til.setEndIconOnClickListener {
            val timePicker = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)
                val time = SimpleDateFormat("hh:mm")
                time_group.setText(time.format(selectedTime.time))
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
            timePicker.show()
        }
    }

    private fun pickDate() {
        context?.let {
            DatePickerDialog(it, dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun setView() {
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)

        viewModel.getAllCourses(1)
        viewModel.allCourses.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                coursesList = response.body()!!
                val courses: MutableList<String> = ArrayList<String>()
                for (i in coursesList.indices) {
                    courses.add(coursesList.get(i).name)
                }
                val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, courses)
                teacher_group.setAdapter(adapter)
                for (i in coursesList.indices) {
                    coursesHashMap = HashMap()
                    coursesHashMap.put(coursesList.get(i).name, coursesList.get(i).id)
                    course_group.setText(coursesList.get(0).name)
                }
            } else {
                Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                Log.d("Groups", "body + " + response.body().toString())
            }
        })

        viewModel.getAllTeachers(1)
        viewModel.allTeachers.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                teachersList = response.body()!!
                val teachers: MutableList<String> = ArrayList<String>()
                for (i in teachersList.indices) {
                    teachers.add(teachersList.get(i).name)
                }
                val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, teachers)
                teacher_group.setAdapter(adapter)
                for (i in teachersList.indices) {
                    teachersHashMap = HashMap()
                    teachersHashMap.put(teachersList.get(i).name, teachersList.get(i).id)
                    teacher_group.setText(teachersList.get(0).name)
                }
            } else {
                Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                Log.d("Groups", "body + " + response.body().toString())
            }
        })

        viewModel.getAllRooms(1)
        viewModel.allRooms.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                roomsList = response.body()!!
                val rooms: MutableList<String> = ArrayList<String>()
                for (i in roomsList.indices) {
                    rooms.add(roomsList.get(i).roomName)
                }
                val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, rooms)
                teacher_group.setAdapter(adapter)
                for (i in roomsList.indices) {
                    roomsHashMap = HashMap()
                    roomsHashMap.put(roomsList.get(i).roomName, roomsList.get(i).id)
                    cabinet_group.setText(roomsList.get(0).roomName)
                }
            } else {
                Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                Log.d("Groups", "body + " + response.body().toString())
            }
        })

        var tags: List<String> = resources.getStringArray(R.array.week_days).toList()
        val inflater: LayoutInflater  = LayoutInflater.from(context)
        for(text: String in tags){
            val chip: Chip = inflater.inflate(R.layout.chip_item, null, false) as Chip
            chip.setText(text)
            chip_group.addView(chip)
        }

    }

    private fun getData() {

    }

    private fun setViewGroup(myGroup: GroupDTO) {
        course_group.setText(myGroup.course.name)
        group_group.setText(myGroup.groupName)
        teacher_group.setText(myGroup.teacher.name)
        //schedule_group.setText(myGroup.timeTable.daysOfWeeks.toString())
        var tags: List<String> = myGroup.timeTable.daysOfWeeks.toString().split(" ")
        val inflater: LayoutInflater  = LayoutInflater.from(context)
        for(text: String in tags){
            val chip: Chip = inflater.inflate(R.layout.chip_item, null, false) as Chip
            chip.setText(text)
            chip_group.addView(chip)
        }
        time_group.setText(myGroup.timeTable.startTime)
        start_date_groups.setText(myGroup.startDate)
        end_date_groups.setText(myGroup.endDate)
        payment_groups.setText(myGroup.amount.toString())
        cabinet_group.setText(myGroup.timeTable.room.roomName)
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val date = SimpleDateFormat("dd-MM-yyyy")
        if (fGroupDate == 0) {
            start_date_groups.setText(date.format(calendar.getTime()))
        } else if (fGroupDate == 1) {
            end_date_groups.setText(date.format(calendar.getTime()))
        }
        fGroupDate = -1
    }

}