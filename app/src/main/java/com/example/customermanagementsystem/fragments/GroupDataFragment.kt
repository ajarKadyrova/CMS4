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
import androidx.navigation.fragment.findNavController
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.*
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
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
    private var calendar = Calendar.getInstance()
    private var fGroupDate = -1
    private var fGroupTime = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_group_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (groupId <= 0) {
            edit_btn_groups.text = resources.getString(R.string.save)
            archive_btn_groups.visibility = View.GONE
            setView()
        } else if (groupId > 0) {
            edit_btn_groups.text = resources.getString(R.string.edit)
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
                } else Toast.makeText(
                    context,
                    resources.getString(R.string.error_loading),
                    Toast.LENGTH_SHORT
                ).show()
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

        start_time_group_til.setEndIconOnClickListener {
            pickTime()
            fGroupTime = 0
        }
        end_time_groups_til.setEndIconOnClickListener {
            pickTime()
            fGroupTime = 1
        }
    }

    private fun pickTime() {
        val timePicker =
            TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedTime.set(Calendar.MINUTE, minute)
                val time = SimpleDateFormat("hh:mm")
                if (fGroupTime == 0) {
                    start_time_groups.setText(time.format(selectedTime.time))
                } else if (fGroupTime == 1) {
                    end_time_groups.setText(time.format(selectedTime.time))
                }
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false)
        timePicker.show()
    }

    private fun pickDate() {
        context?.let {
            DatePickerDialog(
                it, dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    private fun setView() {
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
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
                course_group.setAdapter(adapter)
                for (i in coursesList.indices) {
                    coursesHashMap = HashMap()
                    coursesHashMap[coursesList[i].name] = coursesList[i].id
                }
                //course_group.setText(coursesList[0].name)
            } else {
                Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                Log.d("Groups", "body + " + response.body().toString())
            }
        })

        viewModel.getAllTeachers(1)
        viewModel.allTeachers.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                teachersList = response.body()!!
                val teachers: MutableList<String> = ArrayList()
                for (i in teachersList.indices) {
                    teachers.add(teachersList[i].name)
                }
                val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, teachers)
                teacher_group.setAdapter(adapter)
                for (i in teachersList.indices) {
                    teachersHashMap = HashMap()
                    teachersHashMap[teachersList[i].name] = teachersList[i].id

                }
                //teacher_group.setText(teachersList[0].name)
            } else {
                Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                Log.d("Groups", "body + " + response.body().toString())
            }
        })

        viewModel.getAllRooms(1)
        viewModel.allRooms.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                roomsList = response.body()!!
                val rooms: MutableList<String> = ArrayList()
                for (i in roomsList.indices) {
                    rooms.add(roomsList[i].roomName)
                }
                val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, rooms)
                cabinet_group.setAdapter(adapter)
                for (i in roomsList.indices) {
                    roomsHashMap = HashMap()
                    roomsHashMap[roomsList[i].roomName] = roomsList[i].id
                }
                //cabinet_group.setText(roomsList[0].roomName)
            } else {
                Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                Log.d("Groups", "body + " + response.body().toString())
            }
        })

        val tags: List<String> = resources.getStringArray(R.array.week_days).toList()
        val inflater: LayoutInflater = LayoutInflater.from(context)
        for (text: String in tags) {
            val chip: Chip = inflater.inflate(R.layout.chip_item, null, false) as Chip
            chip.text = text
            chip_group.addView(chip)
        }

    }

    private fun getData() {
        val course = course_group.text.toString()
        val groupName = groupName_group.text.toString()
        val teacher = teacher_group.text.toString()
        val startTime = start_time_groups.text.toString()
        val endTime = end_time_groups.text.toString()
        val startDate = start_date_groups.text.toString()
        val endDate = end_date_groups.text.toString()
        val payment = payment_groups.text.toString().toInt()
        val cabinet = cabinet_group.text.toString()
        if (course.isEmpty()) {
            Toast.makeText(context, resources.getString(R.string.enter_course), Toast.LENGTH_SHORT).show()
        } else if (groupName.isEmpty()) {
            Toast.makeText(context, resources.getString(R.string.enter_groupName), Toast.LENGTH_SHORT).show()
        } else if (teacher.isEmpty()) {
            Toast.makeText(context, resources.getString(R.string.enter_teacher), Toast.LENGTH_SHORT).show()
        } else if (startTime.isEmpty() || endTime.isEmpty()) {
            Toast.makeText(context, resources.getString(R.string.enter_time), Toast.LENGTH_SHORT).show()
        } else if (startDate.isEmpty() || endDate.isEmpty()) {
            Toast.makeText(context, resources.getString(R.string.enter_dates), Toast.LENGTH_SHORT).show()
        } else if (payment.equals("")) {
            Toast.makeText(context, resources.getString(R.string.enter_payment), Toast.LENGTH_SHORT).show()
        } else if (cabinet.isEmpty()) {
            Toast.makeText(context, resources.getString(R.string.enter_cabinet), Toast.LENGTH_SHORT).show()
        }
        var chosenCourse: Long = 0
        var chosenTeacher: Long = 0
        var chosenCabinet: Long = 0
        if (coursesHashMap.containsKey(course)) {
            chosenCourse = coursesHashMap[course]!!
        }
        if (teachersHashMap.containsKey(teacher)) {
            chosenTeacher = teachersHashMap[teacher]!!
        }
        if (roomsHashMap.containsKey(cabinet)) {
            chosenCabinet = roomsHashMap[cabinet]!!
        }
        val schedule: MutableList<String> = ArrayList()
        val days: MutableList<String> = ArrayList()
        for (i in 0 until chip_group.childCount) {
            val chip = chip_group.getChildAt(i) as Chip
            if (chip.isChecked) {
                if (i < chip_group.childCount - 1) schedule.add(chip.text as String)
                else schedule.add(chip.text as String)
                when (chip.text) {
                    "Понедельник" -> days.add("MONDAY")
                    "Вторник" -> days.add("TUESDAY")
                    "Среда" -> days.add("WEDNESDAY")
                    "Четверг" -> days.add("THURSDAY")
                    "Пятница" -> days.add("FRIDAY")
                    "Суббота" -> days.add("SATURDAY")
                    "Воскресенье" -> days.add("SUNDAY")
                }
            }
        }
        Log.d(
            "GROUPDATANEW", "$course +$chosenCourse ' ' + $groupName+ ' ' " +
                    "+ $teacher+$chosenTeacher + ' ' +$startTime + ' ' +$endTime + ' '" +
                    " +$startDate+ ' ' +$endDate+ ' ' +$payment+ ' ' +$cabinet+$chosenCabinet + $days"
        )
        val groupCourse = BoardID(chosenCourse)
        val groupTeacher = BoardID(chosenTeacher)
        val groupCabinet = BoardID(chosenCabinet)
        val timeTable = TimeTable(days, endTime, groupCabinet, startTime)
        val newGroup = PostGroup(payment, groupCourse, endDate, groupName, startDate, groupTeacher, timeTable)
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.createGroup(1, newGroup)
        viewModel.newGroup.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Toast.makeText(context, resources.getString(R.string.new_group), Toast.LENGTH_LONG).show()
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_LONG).show()
                Log.d("Groups", "body + " + response.body().toString())
                Log.d("Groups", "code + " + response.code().toString())
            }
        })
    }

    private fun setViewGroup(myGroup: GroupDTO) {
        course_group.setText(myGroup.course.name)
        groupName_group.setText(myGroup.groupName)
        teacher_group.setText(myGroup.teacher.name)
        val tags: List<String> = myGroup.timeTable.daysOfWeeks.toString().split(" ")
        val inflater: LayoutInflater = LayoutInflater.from(context)
        for (text: String in tags) {
            val chip: Chip = inflater.inflate(R.layout.chip_item, null, false) as Chip
            chip.text = text
            chip_group.addView(chip)
        }
        start_time_groups.setText(myGroup.timeTable.startTime)
        end_time_groups.setText(myGroup.timeTable.endTime)
        start_date_groups.setText(myGroup.startDate)
        end_date_groups.setText(myGroup.endDate)
        payment_groups.setText(myGroup.amount.toString())
        cabinet_group.setText(myGroup.timeTable.room.roomName)
    }

    private val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val date = SimpleDateFormat("dd-MM-yyyy")
        if (fGroupDate == 0) {
            start_date_groups.setText(date.format(calendar.time))
        } else if (fGroupDate == 1) {
            end_date_groups.setText(date.format(calendar.time))
        }
        fGroupDate = -1
    }

}