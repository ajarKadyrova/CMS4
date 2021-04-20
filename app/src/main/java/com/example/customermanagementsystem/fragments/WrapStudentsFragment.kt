package com.example.customermanagementsystem.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.CourseDTO
import com.example.customermanagementsystem.pagerAdapter.ClientsPagerAdapter
import com.example.customermanagementsystem.pagerAdapter.StudentsPagerAdapter
import com.example.customermanagementsystem.repository.Repository
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_wrap_students.*


class WrapStudentsFragment : Fragment() {

    private lateinit var viewModel: ViewModel
    private var coursesList: List<CourseDTO> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wrap_students, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager_wrap_students.adapter = ClientsPagerAdapter(childFragmentManager)
        tabLayout_wrap_students.setupWithViewPager(viewPager_wrap_students)
        tabLayout_wrap_students.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager_wrap_students.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        addPage()
    }

    fun addPage() {

        val adapter: StudentsPagerAdapter? = null

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getAllCourses()
        viewModel.allCourses.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                coursesList = response.body()!!
                for (i in coursesList.indices) {
                    val fragmentChild = StudentsFragment()
                    if (adapter != null) {
                        adapter.addFragment(fragmentChild, coursesList[i].name)
                        Log.d("WRAPSTUDENTS", coursesList[i].name)
                        adapter.notifyDataSetChanged()
                        if (adapter.count > 0) tabLayout_wrap_students.setupWithViewPager(viewPager_wrap_students)
                        viewPager_wrap_students.currentItem = adapter.count - 1
                    }
                }
            } else if (!response.isSuccessful) {
                Log.d("WRAPSTUDENTS", response.body().toString())
                Log.d("WRAPSTUDENTS", response.code().toString())
            }
        })
    }
}