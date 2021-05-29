package com.example.customermanagementsystem.fragments

import StudentsPagerAdapter
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.lifecycle.Observer
import com.example.customermanagementsystem.R
import com.example.customermanagementsystem.models.CourseDTO
import com.example.customermanagementsystem.repository.Repository
import com.example.customermanagementsystem.repository.ViewModel
import com.example.customermanagementsystem.repository.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import java.util.*


/*class WrapStudentsFragment : Fragment() {

    private lateinit var viewModel: ViewModel
    private var coursesList: List<CourseDTO> = ArrayList()
    private var tabLayout: TabLayout? = null
    private val viewPager: ViewPager? = null
    private var adapter: StudentsPagerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_wrap_students, container, false)
        adapter = context?.let { StudentsPagerAdapter(fragmentManager, it) }
        viewPager?.adapter = adapter
        tabLayout = view.findViewById<View>(R.id.tabLayout_wrap_students) as TabLayout
        tabLayout!!.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addPage()
    }

    private fun addPage() {

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getAllCourses()
        viewModel.allCourses.observe(viewLifecycleOwner, Observer { response ->
//            if (response.isSuccessful) {
//                coursesList = response.body()!!
//                val boards:MutableList<String> = ArrayList()
//                for (i in coursesList.indices) {
//                    boards.add(coursesList[i].name)
//                }
//                println("WRAPSTUDENTS + $coursesList")
//                println("WRAPSTUDENTS + $boards")
//                for(i in boards){
//                    val fragmentChild = StudentsFragment1()
//                    if (adapter != null) {
//                        adapter.addFragment(fragmentChild, i)
//                        Log.d("WRAPSTUDENTS", i)
//                        adapter.notifyDataSetChanged()
//                        if (adapter.count > 0) tabLayout_wrap_students.setupWithViewPager(viewPager_wrap_students)
//                        viewPager_wrap_students.currentItem = adapter.count - 1
//                    }
//                }
//            } else if (!response.isSuccessful) {
//                Log.d("WRAPSTUDENTS", response.body().toString())
//                Log.d("WRAPSTUDENTS", response.code().toString())
//            }
            if (!response.isSuccessful) {
                Log.d("ANSWER", "Code:" + response.code())
            }
            else if(response.isSuccessful) {
                Log.d("ANSWER", "Code:" + response.code())
                coursesList = response.body()!!
                val boards: MutableList<String> = ArrayList()
                for (i in coursesList.indices) {
                    boards.add(coursesList[i].name)
                }
                for (i in boards.indices) {
                    val fragmentChild = StudentsFragment()
                    val adapter: StudentsPagerAdapter? = null
                    adapter?.addFragment(fragmentChild, boards[i])
                    adapter?.notifyDataSetChanged()
                    if (adapter != null) {
                        if (adapter.count > 0) tabLayout!!.setupWithViewPager(viewPager)
                    }
                    if (adapter != null) {
                        viewPager!!.currentItem = adapter.count - 1
                    }
                }
            }
        })
    }
}*/

class WrapStudentsFragment: Fragment() {
    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    var adapter: StudentsPagerAdapter? = null
    private lateinit var viewModel: ViewModel
    private var coursesList: List<CourseDTO> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_wrap_students, container, false)
        viewPager = view.findViewById<View>(R.id.viewPager_wrap_students) as ViewPager
        adapter = context?.let { StudentsPagerAdapter(fragmentManager, it) }
        viewPager!!.adapter = adapter
        tabLayout = view.findViewById<View>(R.id.tabLayout_wrap_students) as TabLayout
        tabLayout!!.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
        viewModel.getAllCourses()
        viewModel.allCourses.observe(viewLifecycleOwner, Observer { response ->
            if (!response.isSuccessful) {
                Log.d("ANSWER", "Code:" + response.code())
            }
            if (response.isSuccessful) {
                coursesList = response.body()!!
                for (i in coursesList.indices) {
                    //val bundle = Bundle()
                    //val body: ClientDTO = coursesList[i]
                    //bundle.putSerializable("students", body)
                    val fragmentChild = StudentsFragment()
                    adapter?.addFragment(fragmentChild, coursesList[i].name)
                    adapter?.notifyDataSetChanged()
                    if(adapter != null) {
                        if (adapter!!.count > 0) tabLayout!!.setupWithViewPager(viewPager)
                        viewPager!!.currentItem = adapter!!.count - 1
                    }
                }
            }
        })
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.search_menu, menu)
//        val search = menu?.findItem(R.id.search)
//        val searchView = search.actionView as? androidx.appcompat.widget.SearchView
//        searchView?.isSubmitButtonEnabled = true
//        searchView?.setOnQueryTextListener(this)
//    }

//    override fun onQueryTextSubmit(query: String?): Boolean {
//        if(query!=null){
//            searchData(query)
//        }
//        return true
//    }
//
//    override fun onQueryTextChange(query: String?): Boolean {
//        if(query!=null){
//            searchData(query)
//        }
//        return true
//    }

//    private fun searchData(query: String){
//        val filter = Filter(null, null, query, null)
//        val repository = Repository()
//        val viewModelFactory = ViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, viewModelFactory).get(ViewModel::class.java)
//        viewModel.getAllBoards(1, filter)
//        viewModel.allBoards.observe(viewLifecycleOwner, Observer { response ->
//            if(response.isSuccessful) {
//                val studentsList = response.body()!!
//                for ()
//                if(response.isSuccessful){
//                    val adapter by lazy{ GroupStudentsAdapter(studentsList) }
//                    textView_students_group.visibility = View.GONE
//                    adapter.setData(studentsList)
//                    recyclerView_group_students.layoutManager = LinearLayoutManager(this.context)
//                    recyclerView_group_students.adapter = adapter
//                }
//            }
//            else Toast.makeText(context, resources.getString(R.string.error_loading), Toast.LENGTH_SHORT).show()
//        })
//    }
}
